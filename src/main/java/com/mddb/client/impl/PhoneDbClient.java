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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhoneDbClient implements DeviceClient {

    private final DeviceMapper deviceMapper;

    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL = "http://phonedb.net/";

    int phoneDbPageNumber = 0;
    final int phoneDbPagesize = 29;


    public List<Device> getDevicesWithPaging() {
        Set<String> urlDevices = getUrlDevices();
        final CopyOnWriteArrayList<Device> devices = new CopyOnWriteArrayList<>();
        try {
            log.info("Getting devices started, devices count is {}, page number is {}", urlDevices.size(), phoneDbPageNumber);
            urlDevices.parallelStream().forEach(url -> {
                try {
                    String urlThread = url + "&d=detailed_specs";
                    String decode = URLDecoder.decode(urlThread, "UTF-8");
                    TimeUnit.MILLISECONDS.sleep(new Random(100).nextInt());
                    Document doc =
                            Jsoup.connect(urlThread)
                                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                                    .referrer("none")
                                    .get();
                    if (doc == null) return;
                    Elements table = doc.select("table");
                    if (table.size() == 0) return;
                    Elements tds = table.get(0).select(("td"));
                    Map<String, String> params = new HashMap<>();
                    for (Element el :
                            tds) {
                        if (el.select(("strong")).size() != 0) {
                            Element key = el.select(("strong")).get(0);
                            Element value = el.nextElementSibling();
                            if (!"".equals(key.toString())) {
                                params.put(key.text(), value == null ? null : value.text());
                            }
                        }
                    }
                    Integer idFromUrl = getIdFromUrl(url);
                    if (idFromUrl == 0) return;
                    devices.add(deviceMapper.mapParamsToDevice(params, idFromUrl));
                } catch (Exception e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            });

            log.info("Devices returned" + devices.size());
        } finally {
            phoneDbPageNumber = phoneDbPageNumber + phoneDbPagesize;
        }

        return devices;
    }

    @Override
    public void resetPageNumber() {
        phoneDbPageNumber = 0;
    }


    @SneakyThrows
    private Set<String> getUrlDevices() {
        final Set<String> urlDevices = new TreeSet<>();
        long before = System.currentTimeMillis();
        log.trace("Start get url devices");
        try {
            log.info("Loading current page is {}", phoneDbPageNumber);
            TimeUnit.MILLISECONDS.sleep(new Random(10).nextInt());
            Document doc;
            doc = getDocument(phoneDbPageNumber);
            if (doc == null) return new HashSet<>();
            Elements elements = doc.getElementsByClass("content_block_title");
            if (elements.size() == 0) return new HashSet<>();
            for (Element element : elements) {
                Element titleElement = element.select("a").first();
                String url = titleElement.attr("href");
                urlDevices.add(URL + url);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        long after = System.currentTimeMillis();
        log.info("url Devices returned= {}, in second {}", urlDevices.size(), (after - before) / 1000000);
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
