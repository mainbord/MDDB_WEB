package com.mddb.client.impl;

import com.mddb.client.DeviceClient;
import com.mddb.domain.Device;
import com.mddb.mapper.DeviceMapper;
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
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhoneDbClient implements DeviceClient {

    private final DeviceMapper deviceMapper;

    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL = "http://phonedb.net/";
    private static final int PHONE_DB_PAGE_SIZE = 29;

    private final AtomicInteger phoneDbPageNumber = new AtomicInteger(0);

    @Override
    public List<Device> getDevicesWithPaging() {
        int pageNumber = phoneDbPageNumber.getAndAdd(PHONE_DB_PAGE_SIZE);
        return getDevicesByPage(pageNumber);
    }

    @Override
    public boolean supportsParallelPageLoading() {
        return true;
    }

    @Override
    public int pageStep() {
        return PHONE_DB_PAGE_SIZE;
    }

    @Override
    public List<Device> getDevicesByPage(int pageNumber) {
        Set<String> urlDevices = getUrlDevices(pageNumber);
        List<Device> devices = new CopyOnWriteArrayList<>();

        log.info("Getting devices started, devices count is {}, page number is {}", urlDevices.size(), pageNumber);

        urlDevices.parallelStream().forEach(url -> {
            try {
                String urlThread = url + "&d=detailed_specs";
                URLDecoder.decode(urlThread, "UTF-8");
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));

                Document doc = Jsoup.connect(urlThread)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .referrer("none")
                        .get();

                if (doc == null) {
                    return;
                }

                Elements table = doc.select("table");
                if (table.isEmpty()) {
                    return;
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
                    return;
                }

                devices.add(deviceMapper.mapParamsToDevice(params, idFromUrl));
            } catch (Exception e) {
                log.error("Failed to load device by url={}", url, e);
            }
        });

        log.info("Devices returned {}", devices.size());
        return devices;
    }

    @Override
    public void resetPageNumber() {
        phoneDbPageNumber.set(0);
    }

    @SneakyThrows
    private Set<String> getUrlDevices(int pageNumber) {
        Set<String> urlDevices = new TreeSet<>();
        long before = System.currentTimeMillis();

        try {
            log.info("Loading current page is {}", pageNumber);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10));

            Document doc = getDocument(pageNumber);
            if (doc == null) {
                return new HashSet<>();
            }

            Elements elements = doc.getElementsByClass("content_block_title");
            if (elements.isEmpty()) {
                return new HashSet<>();
            }

            for (Element element : elements) {
                Element titleElement = element.select("a").first();
                String url = titleElement.attr("href");
                urlDevices.add(URL + url);
            }
        } catch (Exception e) {
            log.error("Failed to load urls for page={}", pageNumber, e);
        }

        long after = System.currentTimeMillis();
        log.info("url Devices returned= {}, in second {}", urlDevices.size(), (after - before) / 1_000_000);
        return urlDevices;
    }

    private Document getDocument(int page) throws IOException {
        String url = String.format(URL_FORMAT, page);
        return Jsoup.connect(url)
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