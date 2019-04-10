package com.mddb.loader;

import com.mddb.domain.Device;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by mainbord on 14.11.17.
 */
@Log4j2(topic = "app")
public class PdaDbLoader implements DbLoader {

    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL = "http://phonedb.net/";

    private Set<String> urlDevices = new TreeSet<>();

    private List<Device> devices = new ArrayList<>();

    @Override
    public List<Device> getDevices() {
        log.trace("Start get devices");
        try {
            for (String url :
                    urlDevices) {
                url = url + "&d=detailed_specs";
                TimeUnit.MILLISECONDS.sleep(new Random(100).nextInt());
                Document doc =
                        Jsoup.connect(url)
                                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                                .referrer("none")
                                .get();
                if (doc == null) break;
                Elements table = doc.select("table");
                if (table.size() == 0) break;
                Elements tds = table.get(0).select(("td"));
                Map<String, String> parameteres = new HashMap<>();
                for (Element el :
                        tds) {
                    if (el.select(("strong")).size() != 0) {
                        Element key = el.select(("strong")).get(0);
                        Element value = el.nextElementSibling();
                        if (!"".equals(key.toString())) {
                            parameteres.put(key.text(), value.text());
                        }
                    }
                }
                Device device = new Device();
                String paramValue = "";
                device.setCompanyName(parameteres.get("Brand"));
                device.setModelName(parameteres.get("Model"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMM", Locale.ENGLISH);
                String ssss = parameteres.getOrDefault("Released","1920 jan");
                device.setReleaseDate(dateFormat.parse( ssss.equalsIgnoreCase("Cancelled") ? "1920 jan" : ssss));
                device.setWidth(parameteres.getOrDefault("Width",""));
                device.setHeight(parameteres.getOrDefault("Height",""));
                device.setDepth(parameteres.getOrDefault("Depth",""));
                device.setWeight(parameteres.getOrDefault("Mass",""));
                device.setOperatingSystem(parameteres.getOrDefault("Platform",""));
                device.setSoc(parameteres.getOrDefault("CPU","").split(",")[0]);
                device.setMaxFrequencyPerCore(parameteres.getOrDefault("CPU Clock","0").split(" ")[0].replaceAll("/.",","));
                device.setRamType(parameteres.getOrDefault("RAM Type",""));
                device.setRamSize((parameteres.getOrDefault("RAM Capacity", "0 0").split(" ")[0]));
                device.setRom(parameteres.getOrDefault("Non-volatile Memory Capacity",""));
                device.setDisplayResolution(parameteres.getOrDefault("Display Resolution",""));
                device.setDisplayDiagonal(parameteres.getOrDefault("Display Diagonal",""));
                device.setDisplayType(parameteres.getOrDefault("Display Type","unknown"));
                device.setDisplayColorAmount(Integer.valueOf(parameteres.getOrDefault("Display Color Depth","0 0").split(" ")[0]));
                device.setGpuControllerName(parameteres.getOrDefault("Graphical Controller",""));
                device.setSpeaker(parameteres.getOrDefault("Loudpeaker(s)",""));
                device.setAntenna(parameteres.getOrDefault("Cellular Antenna","").equalsIgnoreCase("Internal antenna") ? "internal" : "external");
                device.setBluetooth(!parameteres.getOrDefault("Bluetooth","").equalsIgnoreCase("no"));
                device.setWifi(!parameteres.getOrDefault("Wireless LAN","").equalsIgnoreCase("no"));
                device.setInfrared(!parameteres.getOrDefault("Infrared","").equalsIgnoreCase("no"));
                device.setFmReceiver(!parameteres.getOrDefault("FM Radio Receiver","").equalsIgnoreCase("No"));
                device.setBatteryType(parameteres.getOrDefault("Battery",""));
                device.setBatteryCapacity(Integer.valueOf(parameteres.getOrDefault("Nominal Battery Capacity","0 0 ").split(" ")[0]));
                paramValue = parameteres.getOrDefault("Protection from liquids","");
                device.setWaterProof(paramValue.length() == 0 ? 0 : Character.isDigit(paramValue.charAt(0)) ? (int) paramValue.charAt(0) : 0);
                paramValue = parameteres.getOrDefault("Protection from solid materials","");
                device.setDustProof(paramValue.length() == 0 ? 0 : Character.isDigit(paramValue.charAt(0)) ? (int) paramValue.charAt(0) : 0);
                device.setGyroscope(parameteres.getOrDefault("Built-in gyroscope","").equalsIgnoreCase("yes"));
                device.setAccelerometer(parameteres.getOrDefault("Built-in accelerometer","").equalsIgnoreCase("yes"));
                device.setCompass(parameteres.getOrDefault("Built-in compass","").equalsIgnoreCase("yes"));
                devices.add(device);
//                break; //test one
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        log.info("Devices returned" + devices.size());
        return devices;
    }

    public Set<String> getUrlDevices() {
        log.trace("Start get url devices");
        try {
            int pageNumber = 8000; //поменять на 0, это для ускорения результатов тестирования
            Document doc;
            while (true) {
                TimeUnit.MILLISECONDS.sleep(new Random(100).nextInt());
                doc = getDocument(pageNumber);
                if (doc == null) break;
                Elements elements = doc.select("div.content_block_title");
                if (elements.size() == 0) break;

                for (Element element : elements) {
                    // title
                    Element titleElement = element.select("a").first();
                    String url = titleElement.attr("href");
                    urlDevices.add(URL + url);
/*                    // salary
                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text();
                    }

                    // city
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();

                    // company
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();

                    // site
                    String siteName = "http://hh.ua/";

                    // url
                    String url = titleElement.attr("href");

                    // add vacancy to the list
                    Device device = new Device();
                    vacancies.add(device);*/

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

    protected Document getDocument(int page) throws IOException {

        String url = String.format(URL_FORMAT, page);
        Document document =
                Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .referrer("none")
                        .get();

        return document;
    }
}
