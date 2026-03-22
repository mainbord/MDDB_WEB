package com.mddb.client.impl;

import com.mddb.client.DeviceClient;
import com.mddb.domain.Device;
import com.mddb.mapper.DeviceMapper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
// todo
public class DeviceSpecificationsClient implements DeviceClient {

    private final DeviceMapper deviceMapper;

    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL_BRANDS = "https://www.devicespecifications.com/en/brand-more";

    int phoneDbPageNumber = 0;
    final int phoneDbPagesize = 29;

    @SneakyThrows
    public List<Device> getDevicesWithPaging() {

        // todo captcha failed
        String pageWithBrandsUrls = getPageFromBrowserScrolledDown(URL_BRANDS);
        Files.write(Paths.get("/home/max/ss.html"), pageWithBrandsUrls.getBytes(), StandardOpenOption.CREATE);
        Set<String> urlBrands = getBrands(Jsoup.parse(pageWithBrandsUrls));

        urlBrands.stream().forEach(brandUrl -> {
            String pageWithDeviceUrls = getPageFromBrowserScrolledDown(brandUrl);
            Document doc = Jsoup.parse(pageWithDeviceUrls);
            Elements items = doc.select(".item-class"); // Parse with Jsoup as needed

        });

        final CopyOnWriteArrayList<Device> devices = new CopyOnWriteArrayList<>();


//        try {
//            log.info("Getting devices started, devices count is {}, page number is {}", urlDevices.size(), phoneDbPageNumber);
//            urlDevices.parallelStream().forEach(url -> {
//                try {
//                    String urlThread = url + "&d=detailed_specs";
//                    String decode = URLDecoder.decode(urlThread, "UTF-8");
//                    TimeUnit.MILLISECONDS.sleep(new Random(100).nextInt());
//                    Document doc =
//                            Jsoup.connect(urlThread)
//                                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
//                                    .referrer("none")
//                                    .get();
//                    if (doc == null) return;
//                    Elements table = doc.select("table");
//                    if (table.size() == 0) return;
//                    Elements tds = table.get(0).select(("td"));
//                    Map<String, String> params = new HashMap<>();
//                    for (Element el :
//                            tds) {
//                        if (el.select(("strong")).size() != 0) {
//                            Element key = el.select(("strong")).get(0);
//                            Element value = el.nextElementSibling();
//                            if (!"".equals(key.toString())) {
//                                params.put(key.text(), value == null ? null : value.text());
//                            }
//                        }
//                    }
//                    Integer idFromUrl = getIdFromUrl(url);
//                    if (idFromUrl == 0) return;
//                    devices.add(deviceMapper.mapParamsToDevice(params, idFromUrl));
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                    e.printStackTrace();
//                }
//            });
//
//            log.info("Devices returned" + devices.size());
//        } finally {
//            phoneDbPageNumber = phoneDbPageNumber + phoneDbPagesize;
//        }
//
        return devices;
    }

    @Override
    public void resetPageNumber() {
        phoneDbPageNumber = 0;
    }

    private String getPageFromBrowserScrolledDown(String url) {
        String finalHtml = "";
        try (Playwright playwright = Playwright.create()) {
            // 'firefox' can be changed to 'chromium' or 'webkit'
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
//                    .setTimeout(3000)
                    .setHeadless(false)); // Set to true for headless
            Page page = browser.newPage();
            page.navigate(url);
            Object lastHeight = page.evaluate("() => document.body.scrollHeight");
            while (true) {
                page.evaluate("() => window.scrollTo(0, document.body.scrollHeight)");
                page.waitForTimeout(2000); // Wait for content
                Object newHeight = page.evaluate("() => document.body.scrollHeight");
                if (newHeight.equals(lastHeight)) {
                    break;
                }
                lastHeight = newHeight;
            }
            finalHtml = page.content();
            browser.close();
        }
        return finalHtml;
    }

    @SneakyThrows
    private Set<String> getBrands(Document doc) {
        final Set<String> urls = new TreeSet<>();
        try {
            TimeUnit.MILLISECONDS.sleep(new Random(10).nextInt());
            if (doc == null) return new HashSet<>();
            Elements elements = doc.getElementsByClass("brand-listing-container-news");
            if (elements.size() == 0) return new HashSet<>();
            for (Element element : elements.first().children()) {
                Element titleElement = element.select("a").first();
                String url = titleElement.attr("href");
                urls.add(url);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return urls;
    }

    @SneakyThrows
    private Set<String> getUrlDevices(Document doc) {
        final Set<String> urls = new TreeSet<>();
        try {
            TimeUnit.MILLISECONDS.sleep(new Random(10).nextInt());
            if (doc == null) return new HashSet<>();
            Elements elements = doc.getElementsByClass("model-listing-container-80");
            if (elements.size() == 0) return new HashSet<>();
            for (Element element : elements.first().children()) {
                Element titleElement = element.select("a").first();
                String url = titleElement.attr("href");
                urls.add(URL_BRANDS + url);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return urls;
    }


    private Document getDocument() throws IOException {
        return Jsoup.connect(URL_BRANDS)
                .timeout(1000 * 120)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .referrer("none")
                .get();
    }

    private Integer getIdFromUrl(String url) throws URISyntaxException {
        String[] params = new URI(url).getQuery().split("&");
        for (String param : params) {
            if (param.startsWith("id=")) {
                return Integer.valueOf(param.substring(3));
            }
        }
        return 0;
    }

}
