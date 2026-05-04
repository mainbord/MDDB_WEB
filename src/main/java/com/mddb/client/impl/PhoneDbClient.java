package com.mddb.client.impl;

import com.mddb.client.DeviceClient;
import com.mddb.domain.Device;
import com.mddb.mapper.DeviceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhoneDbClient implements DeviceClient {

    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL = "http://phonedb.net/";
    private static final int REQUEST_TIMEOUT_MS = 1000 * 120;
    private static final int RETRY_COUNT = 3;
    private static final int RETRY_DELAY_SECONDS = 3;
    private static final int PHONE_DB_PAGE_SIZE = 29;

    private final DeviceMapper deviceMapper;

    private final AtomicInteger phoneDbPageNumber = new AtomicInteger(0);

    @Override
    public List<Device> getDevicesWithPaging() {
        return getDevicesByPage(phoneDbPageNumber.getAndAdd(PHONE_DB_PAGE_SIZE));
    }

    @Override
    public List<Device> getDevicesByPage(int pageNumber) {
        Set<String> urlDevices = getUrlDevices(pageNumber);
        final List<Device> devices = new CopyOnWriteArrayList<>();

        urlDevices.parallelStream().forEach(url -> {
            Device device = loadDeviceWithRetry(url);
            if (device != null) {
                devices.add(device);
            }
        });

        return devices;
    }

    @Override
    public void resetPageNumber() {
        phoneDbPageNumber.set(0);
    }

    private Device loadDeviceWithRetry(String url) {
        for (int attempt = 1; attempt <= RETRY_COUNT; attempt++) {
            try {
                return loadDevice(url);
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

    private Device loadDevice(String url) throws Exception {
        String urlThread = url + "&d=detailed_specs";

        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100));

        Document doc = getDocumentWithRetry(urlThread);
        if (doc == null) {
            return null;
        }

        Elements table = doc.select("table");
        if (table.isEmpty()) {
            return null;
        }

        Elements tds = table.get(0).select("td");
        Map<String, String> params = new HashMap<>();

        for (Element el : tds) {
            if (!el.select("strong").isEmpty()) {
                Element key = el.select("strong").get(0);
                Element value = el.nextElementSibling();
                if (!"".equals(key.toString())) {
                    params.put(key.text(), value == null ? null : value.text());
                }
            }
        }

        Integer idFromUrl = getIdFromUrl(url);
        if (idFromUrl == 0) {
            return null;
        }

        return deviceMapper.mapParamsToDevice(params, idFromUrl);
    }

    private Document getDocumentWithRetry(String url) throws IOException, InterruptedException {
        IOException lastException = null;

        for (int attempt = 1; attempt <= RETRY_COUNT; attempt++) {
            try {
                return Jsoup.connect(url)
                        .timeout(REQUEST_TIMEOUT_MS)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .referrer(URL)
                        .get();
            } catch (IOException e) {
                lastException = e;

                if (attempt == RETRY_COUNT) {
                    throw e;
                }

                log.warn(
                        "Request failed. url={}, attempt {}/{}. Retry after {} seconds",
                        url, attempt, RETRY_COUNT, RETRY_DELAY_SECONDS, e
                );
                sleepBeforeRetry();
            }
        }

        throw lastException;
    }

    private Set<String> getUrlDevices(int pageNumber) {
        final Set<String> urlDevices = new TreeSet<>();
        long before = System.currentTimeMillis();

        try {
            log.info("Loading current page is {}", pageNumber);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10));

            Document doc = getPageDocumentWithRetry(pageNumber);
            if (doc == null) {
                throw new IllegalStateException("Document is null for page " + pageNumber);
            }

            Elements elements = doc.getElementsByClass("content_block_title");
            if (elements.isEmpty()) {
                return new HashSet<>();
            }

            for (Element element : elements) {
                Element titleElement = element.select("a").first();
                if (titleElement != null) {
                    String url = titleElement.attr("href");
                    urlDevices.add(URL + url);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load page " + pageNumber, e);
        } finally {
            long after = System.currentTimeMillis();
            log.info("Url devices returned count = {}, first {}, in milliseconds {}", urlDevices.size(), urlDevices.stream().findFirst(), after - before);
        }

        return urlDevices;
    }

    private Document getPageDocumentWithRetry(int page) throws IOException, InterruptedException {
        String url = String.format(URL_FORMAT, page);
        return getDocumentWithRetry(url);
    }

    private void sleepBeforeRetry() {
        try {
            TimeUnit.SECONDS.sleep(RETRY_DELAY_SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Retry sleep interrupted", e);
        }
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

    @Override
    public boolean supportsParallelPageLoading() {
        return true;
    }

    @Override
    public int pageStep() {
        return PHONE_DB_PAGE_SIZE;
    }


}
