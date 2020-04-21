package com.mddb.client.impl;

import com.mddb.client.DeviceClient;
import com.mddb.domain.Device;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import static java.util.Objects.isNull;

@Component
@Log4j2
public class PhoneDbClient implements DeviceClient {
    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL = "http://phonedb.net/";

    public List<Device> getDevices() {
        Set<String> urlDevices = getUrlDevices();
        log.trace("Getting devices started");

        final CopyOnWriteArrayList<Device> devices = new CopyOnWriteArrayList<>();

        urlDevices.parallelStream().forEach(url -> {
            try {
                String urlThread = url + "&d=detailed_specs";
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
                            params.put(key.text(), value.text());
                        }
                    }
                }
                devices.add(mapParamsToDevice(params));
//                break; //test one
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });

        log.info("Devices returned" + devices.size());
        return devices;
    }

    @SneakyThrows
    private Device mapParamsToDevice(Map<String, String> params) {
        if (isNull(params) || params.isEmpty()) return null;
        Device device = Device.builder()
                .companyName(params.get("Brand"))
                .modelName(params.get("Model"))
                .build();
        String releasedDate = params.getOrDefault("Released", "1920 jan");
        device.setReleaseDate(new SimpleDateFormat("yyyy MMM", Locale.ENGLISH).parse(releasedDate.equalsIgnoreCase("Cancelled") ? "1920 jan" : releasedDate));
        device.setWidth(params.getOrDefault("Width", ""));
        device.setHeight(params.getOrDefault("Height", ""));
        device.setDepth(params.getOrDefault("Depth", ""));
        device.setWeight(params.getOrDefault("Mass", ""));
        device.setOperatingSystem(params.getOrDefault("Platform", ""));
        device.setSoc(params.getOrDefault("CPU", "").split(",")[0]);
        device.setMaxFrequencyPerCore(params.getOrDefault("CPU Clock", "0").split(" ")[0].replaceAll("/.", ","));
        device.setRamType(params.getOrDefault("RAM Type", ""));
        device.setRamSize((params.getOrDefault("RAM Capacity", "0 0").split(" ")[0]));
        device.setRom(params.getOrDefault("Non-volatile Memory Capacity", ""));
        device.setDisplayResolution(params.getOrDefault("Display Resolution", ""));
        device.setDisplayDiagonal(params.getOrDefault("Display Diagonal", ""));
        device.setDisplayType(params.getOrDefault("Display Type", "unknown"));
        device.setDisplayColorAmount(Integer.valueOf(params.getOrDefault("Display Color Depth", "0 0").split(" ")[0]));
        device.setGpuControllerName(params.getOrDefault("Graphical Controller", ""));
        device.setSpeaker(params.getOrDefault("Loudpeaker(s)", ""));
        device.setAntenna(params.getOrDefault("Cellular Antenna", "").equalsIgnoreCase("Internal antenna") ? "internal" : "external");
        device.setBluetooth(!params.getOrDefault("Bluetooth", "").equalsIgnoreCase("no"));
        device.setWifi(!params.getOrDefault("Wireless LAN", "").equalsIgnoreCase("no"));
        device.setInfrared(!params.getOrDefault("Infrared", "").equalsIgnoreCase("no"));
        device.setFmReceiver(!params.getOrDefault("FM Radio Receiver", "").equalsIgnoreCase("No"));
        device.setBatteryType(params.getOrDefault("Battery", ""));
        device.setBatteryCapacity(Integer.valueOf(params.getOrDefault("Nominal Battery Capacity", "0 0 ").split(" ")[0]));
        String liquidsProtection = params.getOrDefault("Protection from liquids", "");
        device.setWaterProof(liquidsProtection.length() == 0 ? 0 : Character.isDigit(liquidsProtection.charAt(0)) ? (int) liquidsProtection.charAt(0) : 0);
        String solidProtection = params.getOrDefault("Protection from solid materials", "");
        device.setDustProof(solidProtection.length() == 0 ? 0 : Character.isDigit(solidProtection.charAt(0)) ? (int) solidProtection.charAt(0) : 0);
        device.setGyroscope(params.getOrDefault("Built-in gyroscope", "").equalsIgnoreCase("yes"));
        device.setAccelerometer(params.getOrDefault("Built-in accelerometer", "").equalsIgnoreCase("yes"));
        device.setCompass(params.getOrDefault("Built-in compass", "").equalsIgnoreCase("yes"));
        return device;
    }

    @SneakyThrows
    private Set<String> getUrlDevices() {
        final Set<String> urlDevices = new TreeSet<>();
        log.trace("Start get url devices");
        try {
            int pageNumber = 8000; //поменять на 0, это для ускорения результатов тестирования
            while (true) {
                final int currentPageNumber = pageNumber;
                TimeUnit.MILLISECONDS.sleep(new Random(100).nextInt());
                Document doc;
                doc = getDocument(currentPageNumber);
                if (doc == null) break;
                Elements elements = doc.getElementsByClass("content_block_title");
                if (elements.size() == 0) break;
                for (Element element : elements) {
                    Element titleElement = element.select("a").first();
                    String url = titleElement.attr("href");
                    urlDevices.add(URL + url);
                }
                //*10 убрать в конечном варианте, сейчас это для ускорения работы
                pageNumber += 58 * 10;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        log.info("url Devices returned" + urlDevices.size());
        return urlDevices;
    }

    private Document getDocument(int page) throws IOException {
        String url = String.format(URL_FORMAT, page);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .referrer("none")
                .get();
    }
}
