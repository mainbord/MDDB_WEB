package com.mddb.loader;

import com.mddb.dao.DeviceRepository;
import com.mddb.domain.Device;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mainbord on 14.11.17.
 */
public class PdaDbLoader implements DbLoader {
    private static Logger log = Logger.getLogger("123");

    private static final String URL_FORMAT = "http://phonedb.net/index.php?m=device&s=list&filter=%d";
    private static final String URL = "http://phonedb.net/";

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMM", Locale.ENGLISH);
        System.out.println(dateFormat.format(new Date()));
/*        PdaDbLoader loader = new PdaDbLoader();
        loader.getUrlDevices();
        loader.getDevices();*/
    }

    private Set<String> urlDevices = new TreeSet<>();

    private List<Device> devices = new ArrayList<>();

    @Override
    public List<Device> getDevices() {
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
                            System.out.println(key.text() + ": " + value.text());
                        }
                    }
                }
                String test = parameteres.get("Brand");
                Device device = new Device();
                device.setCompanyName(parameteres.get("Brand"));
                device.setModelName(parameteres.get("Model"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMM", Locale.ENGLISH);
                device.setReleaseDate(dateFormat.parse(parameteres.get("Released")));
                device.setWidth(parameteres.get("Width"));
                device.setHeight(parameteres.get("Height"));
                device.setDepth(parameteres.get("Depth"));
                device.setWeight(parameteres.get("Mass"));
                device.setOperatingSystem(parameteres.get("Platform"));
                device.setSoc(parameteres.get("CPU"));
                device.setRamType(parameteres.get("RAM Type"));
                device.setRamSize(Integer.valueOf(parameteres.get("RAM Capacity").split(" ")[0]));
                device.setRom(parameteres.get("Non-volatile Memory Capacity"));
                device.setDisplayResolution(parameteres.get("Display Resolution"));
                device.setDisplayDiagonal(parameteres.get("Display Diagonal"));
                device.setDisplayType(parameteres.get("Display Type"));
                device.setDisplayColorAmount(Integer.valueOf(parameteres.get("Display Color Depth").split(" ")[0]));
                device.setGpuControllerName(parameteres.get("Graphical Controller"));
                device.setSpeaker(parameteres.get("Loudpeaker(s)"));
                devices.add(device);
//                break; //test one
            }
        } catch (Exception e) {
            log.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
        return devices;
    }

    public Set<String> getUrlDevices() {
        try {
            int pageNumber = 12500; //поменять на 0, это для ускорения результатов тестирования
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
            log.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
        return urlDevices;
    }

    protected Document getDocument(int page) throws IOException {

        String url = String.format(URL_FORMAT, page);
/*        String url = URL_FORMAT;*/
        Document document =
                Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .referrer("none")
                        .get();

        return document;
    }
}
