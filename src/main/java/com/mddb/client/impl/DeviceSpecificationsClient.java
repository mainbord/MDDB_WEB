package com.mddb.client.impl;

import com.mddb.client.DeviceClient;
import com.mddb.domain.Device;
import com.mddb.mapper.DeviceMapper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitUntilState;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeviceSpecificationsClient implements DeviceClient {

    private static final String BASE_URL = "https://www.devicespecifications.com";
    private static final String URL_BRANDS = BASE_URL + "/en/brand-more";
    private static final int REQUEST_TIMEOUT_MS = 1000 * 120;
    private static final int RETRY_COUNT = 3;
    private static final int RETRY_DELAY_SECONDS = 3;
    private static final int HUMAN_VERIFICATION_WAIT_MS = 45_000;
    private static final int PAGE_SETTLE_TIMEOUT_MS = 5_000;
    private static final String HUMAN_VERIFICATION_MESSAGE = "DeviceSpecifications returned a human verification page. "
            + "The loader cannot parse brands until the site allows the request.";
    private static final Pattern FREQUENCY_PATTERN = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*GHz|(\\d+)\\s*MHz", Pattern.CASE_INSENSITIVE);
    private static final Pattern CAPACITY_PATTERN = Pattern.compile("(\\d+)\\s*mAh", Pattern.CASE_INSENSITIVE);

    private final DeviceMapper deviceMapper;

    private int brandPageNumber = 0;
    private List<String> brandUrls = List.of();

    @Override
    public List<Device> getDevicesWithPaging() {
        if (brandUrls.isEmpty()) {
            brandUrls = new ArrayList<>(getBrands(getDocumentWithRetry(URL_BRANDS)));
        }

        if (brandPageNumber >= brandUrls.size()) {
            return List.of();
        }

        String brandUrl = brandUrls.get(brandPageNumber);
        brandPageNumber++;

        Set<String> deviceUrls = getUrlDevices(getDocumentWithRetry(brandUrl));
        List<Device> devices = new CopyOnWriteArrayList<>();

        deviceUrls.stream().forEach(url -> {
            Device device = loadDeviceWithRetry(url);
            if (device != null) {
                devices.add(device);
            }
        });

        log.info("Loaded {} devices from brand page {}", devices.size(), brandUrl);
        return devices;
    }

    @Override
    public void resetPageNumber() {
        brandPageNumber = 0;
        brandUrls = List.of();
    }

    private Device loadDeviceWithRetry(String url) {
        for (int attempt = 1; attempt <= RETRY_COUNT; attempt++) {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
                return parseDevice(getDocumentWithRetry(url), url);
            } catch (Exception e) {
                if (attempt == RETRY_COUNT) {
                    log.error("Failed to load device by url={} after {} attempts", url, RETRY_COUNT, e);
                    return null;
                }

                log.warn(
                        "Failed to load device by url={}, attempt {}/{}. Retry after {} seconds",
                        url, attempt, RETRY_COUNT, RETRY_DELAY_SECONDS, e
                );
                sleepBeforeRetry();
            }
        }

        return null;
    }

    Set<String> getBrands(Document doc) {
        assertParseablePage(doc);
        if (doc == null) {
            return Set.of();
        }

        Set<String> urls = new TreeSet<>();
        for (Element link : doc.select(".brand-listing-container-news a[href]")) {
            urls.add(normalizeUrl(link.attr("href")));
        }
        return urls;
    }

    Set<String> getUrlDevices(Document doc) {
        assertParseablePage(doc);
        if (doc == null) {
            return Set.of();
        }

        Set<String> urls = new TreeSet<>();
        Elements links = doc.select("div.model-listing-container-80 h3 a[href]");
        if (links.isEmpty()) {
            links = doc.select(".model-listing-container-80 a[href]");
        }

        for (Element link : links) {
            String url = normalizeUrl(link.attr("href"));
            if (url.contains("/en/model/")) {
                urls.add(url);
            }
        }
        return urls;
    }

    Device parseDevice(Document doc, String url) {
        assertParseablePage(doc);
        if (doc == null) {
            return null;
        }

        Map<String, String> sourceParams = getBriefSpecifications(doc);
        if (sourceParams.isEmpty()) {
            return null;
        }

        Map<String, String> params = mapToPhoneDbParams(sourceParams, doc);
        return deviceMapper.mapParamsToDevice(params, getIdFromUrl(url));
    }

    private Map<String, String> getBriefSpecifications(Document doc) {
        Map<String, String> params = new HashMap<>();
        Elements keys = doc.select("div#model-brief-specifications b");

        for (Element key : keys) {
            String value = textAfter(key);
            if (!value.isBlank()) {
                params.put(cleanKey(key.text()), value);
            }
        }

        return params;
    }

    private Map<String, String> mapToPhoneDbParams(Map<String, String> sourceParams, Document doc) {
        Map<String, String> params = new HashMap<>();
        String title = extractTitle(doc);

        params.put("Brand", extractBrand(doc, title));
        params.put("Model", extractModel(doc, title));
        params.put("Released", "1920 jan");

        putDimensions(params, sourceParams.get("Dimensions"));
        params.put("Mass", sourceParams.getOrDefault("Weight", ""));
        params.put("Platform", sourceParams.getOrDefault("OS", ""));
        params.put("CPU", firstNonBlank(sourceParams.get("SoC"), sourceParams.get("CPU")));
        params.put("CPU Clock", extractMaxFrequency(sourceParams.get("CPU")));
        params.put("Graphical Controller", sourceParams.getOrDefault("GPU", ""));
        putRam(params, sourceParams.get("RAM"));
        params.put("Non-volatile Memory Capacity", sourceParams.getOrDefault("Storage", ""));
        putDisplay(params, sourceParams.get("Display"));
        putBattery(params, sourceParams.get("Battery"));
        params.put("Wireless LAN", yesNo(sourceParams.get("Wi-Fi")));
        params.put("Bluetooth", yesNo(sourceParams.get("Bluetooth")));
        params.put("Supported GPS protocol(s)", sourceParams.getOrDefault("Positioning", ""));

        return params;
    }

    private void putDimensions(Map<String, String> params, String dimensions) {
        if (dimensions == null || dimensions.isBlank()) {
            return;
        }

        String[] parts = dimensions.replace("Dimensions:", "").trim().split("\\s*x\\s*");
        if (parts.length > 0) {
            params.put("Width", normalizeMillimeters(parts[0]));
        }
        if (parts.length > 1) {
            params.put("Height", normalizeMillimeters(parts[1]));
        }
        if (parts.length > 2) {
            params.put("Depth", normalizeMillimeters(parts[2]));
        }
    }

    private void putRam(Map<String, String> params, String ram) {
        if (ram == null || ram.isBlank()) {
            return;
        }

        String[] parts = ram.split(",");
        params.put("RAM Capacity", parts[0].trim());
        if (parts.length > 1) {
            params.put("RAM Type", parts[1].trim());
        }
    }

    private void putDisplay(Map<String, String> params, String display) {
        if (display == null || display.isBlank()) {
            return;
        }

        String[] parts = display.split(",");
        if (parts.length > 0) {
            params.put("Display Diagonal", parts[0].trim());
        }
        if (parts.length > 1) {
            params.put("Display Type", parts[1].trim());
        }
        if (parts.length > 2) {
            params.put("Display Resolution", parts[2].trim());
        }
        if (parts.length > 3) {
            params.put("Display Color Depth", parts[3].trim());
        }
    }

    private void putBattery(Map<String, String> params, String battery) {
        if (battery == null || battery.isBlank()) {
            return;
        }

        params.put("Battery", battery);
        Matcher matcher = CAPACITY_PATTERN.matcher(battery);
        if (matcher.find()) {
            params.put("Nominal Battery Capacity", matcher.group(1) + " mAh");
        }
    }

    private String extractTitle(Document doc) {
        Element h1 = doc.selectFirst("h1");
        if (h1 != null && !h1.text().isBlank()) {
            return h1.text().replace(" - Specifications", "").trim();
        }

        String title = doc.title();
        int separatorIndex = title.indexOf(" - ");
        return separatorIndex > 0 ? title.substring(0, separatorIndex).trim() : title.trim();
    }

    private String extractBrand(Document doc, String title) {
        Element brand = doc.select("a[href*=brand]").last();
        if (brand != null && !brand.text().isBlank()) {
            return brand.text().trim();
        }

        int spaceIndex = title.indexOf(' ');
        return spaceIndex > 0 ? title.substring(0, spaceIndex) : title;
    }

    private String extractModel(Document doc, String title) {
        Element current = doc.select(".breadcrumb span, .breadcrumbs span").last();
        if (current != null && !current.text().isBlank()) {
            return current.text().trim();
        }

        String brand = extractBrand(doc, title);
        return title.startsWith(brand + " ") ? title.substring(brand.length() + 1).trim() : title;
    }

    private String textAfter(Element element) {
        StringBuilder value = new StringBuilder();
        for (Node sibling = element.nextSibling(); sibling != null; sibling = sibling.nextSibling()) {
            if (sibling instanceof Element siblingElement && "br".equalsIgnoreCase(siblingElement.tagName())) {
                break;
            }
            if (sibling instanceof TextNode textNode) {
                value.append(textNode.text());
            } else if (sibling instanceof Element siblingElement) {
                value.append(siblingElement.text());
            }
        }
        return value.toString().replaceFirst("^\\s*:\\s*", "").trim();
    }

    private String extractMaxFrequency(String cpu) {
        if (cpu == null || cpu.isBlank()) {
            return "0";
        }

        double maxMhz = 0;
        Matcher matcher = FREQUENCY_PATTERN.matcher(cpu);
        while (matcher.find()) {
            double value = matcher.group(1) != null
                    ? Double.parseDouble(matcher.group(1)) * 1000
                    : Double.parseDouble(matcher.group(2));
            maxMhz = Math.max(maxMhz, value);
        }

        return maxMhz == 0 ? "0" : String.valueOf((int) maxMhz);
    }

    private String normalizeMillimeters(String value) {
        String normalized = value.trim();
        return normalized.toLowerCase().contains("mm") ? normalized : normalized + " mm";
    }

    private String yesNo(String value) {
        return value == null || value.isBlank() ? "no" : "yes";
    }

    private String firstNonBlank(String first, String second) {
        return first == null || first.isBlank() ? second == null ? "" : second : first;
    }

    private String cleanKey(String key) {
        return key.replace(":", "").trim();
    }

    private boolean isHumanVerificationPage(Document doc) {
        return doc.text().toLowerCase().contains("verify you are human");
    }

    private void assertParseablePage(Document doc) {
        if (doc != null && isHumanVerificationPage(doc)) {
            throw new HumanVerificationRequiredException(HUMAN_VERIFICATION_MESSAGE);
        }
    }

    private String normalizeUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        if (url.startsWith("/")) {
            return BASE_URL + url;
        }
        return BASE_URL + "/" + url;
    }

    private Document getDocumentWithRetry(String url) {
        RuntimeException lastException = null;

        for (int attempt = 1; attempt <= RETRY_COUNT; attempt++) {
            try {
                Document document = getDocumentFromBrowser(url);
                assertParseablePage(document);
                return document;
            } catch (HumanVerificationRequiredException e) {
                lastException = e;
            } catch (RuntimeException e) {
                lastException = e;
            }

            if (attempt == RETRY_COUNT) {
                throw new RuntimeException("Request failed. url=" + url, lastException);
            }

            log.warn(
                    "Request failed. url={}, attempt {}/{}. Retry after {} seconds",
                    url, attempt, RETRY_COUNT, RETRY_DELAY_SECONDS, lastException
            );
            sleepBeforeRetry();
        }

        throw new RuntimeException("Request failed. url=" + url, lastException);
    }

    private Document getDocumentFromBrowser(String url) {
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                     .setHeadless(isHeadlessBrowser())
                     .setTimeout((double) REQUEST_TIMEOUT_MS))) {
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .setLocale("en-US")
                    .setViewportSize(1366, 900));
            Page page = context.newPage();
            page.setDefaultTimeout(REQUEST_TIMEOUT_MS);
            page.setDefaultNavigationTimeout(REQUEST_TIMEOUT_MS);
            page.navigate(url, new Page.NavigateOptions()
                    .setReferer(BASE_URL)
                    .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
            waitUntilHumanVerificationPasses(page, url);
            waitForExpectedContent(page, url);
            scrollToBottom(page);

            String html = page.content();
            context.close();
            return Jsoup.parse(html, url);
        } catch (PlaywrightException e) {
            throw new RuntimeException("Failed to render page with Playwright. url=" + url, e);
        }
    }

    private void waitUntilHumanVerificationPasses(Page page, String url) {
        if (!isHumanVerificationText(page.content())) {
            return;
        }

        log.info("DeviceSpecifications human verification page detected. Waiting for browser challenge, url={}", url);
        long deadline = System.currentTimeMillis() + HUMAN_VERIFICATION_WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            page.waitForTimeout(1_000);
            if (!isHumanVerificationText(page.content())) {
                return;
            }
        }

        throw new HumanVerificationRequiredException(HUMAN_VERIFICATION_MESSAGE);
    }

    private void waitForExpectedContent(Page page, String url) {
        String selector = expectedSelector(url);
        if (selector == null) {
            return;
        }

        try {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.ATTACHED)
                    .setTimeout((double) REQUEST_TIMEOUT_MS));
        } catch (PlaywrightException e) {
            if (isHumanVerificationText(page.content())) {
                throw new HumanVerificationRequiredException(HUMAN_VERIFICATION_MESSAGE);
            }
            throw e;
        }
    }

    private String expectedSelector(String url) {
        if (URL_BRANDS.equals(url)) {
            return ".brand-listing-container-news a[href]";
        }
        if (url.contains("/en/brand/")) {
            return ".model-listing-container-80 a[href]";
        }
        if (url.contains("/en/model/")) {
            return "#model-brief-specifications b";
        }
        return null;
    }

    private void scrollToBottom(Page page) {
        Object lastHeight = page.evaluate("() => document.body.scrollHeight");
        while (true) {
            page.evaluate("() => window.scrollTo(0, document.body.scrollHeight)");
            page.waitForTimeout(PAGE_SETTLE_TIMEOUT_MS);
            Object newHeight = page.evaluate("() => document.body.scrollHeight");
            if (newHeight.equals(lastHeight)) {
                break;
            }
            lastHeight = newHeight;
        }
    }

    private boolean isHumanVerificationText(String html) {
        return html != null && html.toLowerCase().contains("verify you are human");
    }

    private boolean isHeadlessBrowser() {
        String value = System.getenv().getOrDefault("DEVICESPECIFICATIONS_HEADLESS", "false");
        return Boolean.parseBoolean(value);
    }

    private void sleepBeforeRetry() {
        try {
            TimeUnit.SECONDS.sleep(RETRY_DELAY_SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Retry sleep interrupted", e);
        }
    }

    private Integer getIdFromUrl(String url) {
        int hash = url.hashCode();
        return hash == 0 ? 1 : hash;
    }

    static class HumanVerificationRequiredException extends RuntimeException {
        HumanVerificationRequiredException(String message) {
            super(message);
        }
    }
}
