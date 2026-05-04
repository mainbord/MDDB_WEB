package com.mddb.client.impl;

import com.mddb.domain.Device;
import com.mddb.mapper.DeviceMapperImpl;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeviceSpecificationsClientTest {

    private final DeviceSpecificationsClient client = new DeviceSpecificationsClient(new DeviceMapperImpl());

    @Test
    void getBrands_shouldParseBrandLinks() {
        String html = """
                <div class="brand-listing-container-news">
                    <div><a href="/en/brand/36f">Samsung</a></div>
                    <div><a href="https://www.devicespecifications.com/en/brand/abc">Apple</a></div>
                </div>
                """;

        Set<String> urls = client.getBrands(Jsoup.parse(html));

        assertEquals(
                Set.of(
                        "https://www.devicespecifications.com/en/brand/36f",
                        "https://www.devicespecifications.com/en/brand/abc"
                ),
                urls
        );
    }

    @Test
    void getBrands_shouldFailFastOnHumanVerificationPage() {
        String html = """
                <html>
                    <body>
                        <p>Verify you are human</p>
                    </body>
                </html>
                """;

        assertThrows(
                DeviceSpecificationsClient.HumanVerificationRequiredException.class,
                () -> client.getBrands(Jsoup.parse(html))
        );
    }

    @Test
    void getUrlDevices_shouldParseModelLinks() {
        String html = """
                <div class="model-listing-container-80">
                    <h3><a href="/en/model/89ef5bb4">Samsung Galaxy S23</a></h3>
                    <h3><a href="/en/compare/89ef5bb4">Compare</a></h3>
                </div>
                """;

        Set<String> urls = client.getUrlDevices(Jsoup.parse(html));

        assertEquals(Set.of("https://www.devicespecifications.com/en/model/89ef5bb4"), urls);
    }

    @Test
    void parseDevice_shouldMapBriefSpecificationsToDevice() {
        String html = """
                <html>
                    <head><title>Samsung Galaxy S23 - Specifications</title></head>
                    <body>
                        <a href="/en/brand/36f">Samsung</a>
                        <h1>Samsung Galaxy S23 - Specifications</h1>
                        <div id="model-brief-specifications">
                            <b>Dimensions:</b> 70.9 x 146.3 x 7.6 mm<br>
                            <b>Weight:</b> 168 g<br>
                            <b>SoC:</b> Qualcomm Snapdragon 8 Gen 2 for Galaxy<br>
                            <b>CPU:</b> 1x 3.36 GHz Cortex-X3, 2x 2.8 GHz Cortex-A715<br>
                            <b>GPU:</b> Qualcomm Adreno 740, 719 MHz<br>
                            <b>RAM:</b> 8 GB, 4266 MHz<br>
                            <b>Storage:</b> 128 GB, 256 GB<br>
                            <b>Display:</b> 6.1 in, Dynamic AMOLED 2X, 1080 x 2340 pixels, 30 bit<br>
                            <b>Battery:</b> 3900 mAh, Li-Polymer<br>
                            <b>OS:</b> Android 13<br>
                            <b>Wi-Fi:</b> a, b, g, n, ac<br>
                            <b>Bluetooth:</b> 5.3<br>
                            <b>Positioning:</b> GPS, GLONASS<br>
                        </div>
                    </body>
                </html>
                """;

        Device device = client.parseDevice(Jsoup.parse(html), "https://www.devicespecifications.com/en/model/89ef5bb4");

        assertNotNull(device);
        assertEquals("Samsung", device.getCompanyName());
        assertEquals("Galaxy S23", device.getModelName());
        assertEquals("70.9 mm", device.getWidth());
        assertEquals("146.3 mm", device.getHeight());
        assertEquals("7.6 mm", device.getDepth());
        assertEquals("168 g", device.getWeight());
        assertEquals("Qualcomm Snapdragon 8 Gen 2 for Galaxy", device.getSoc());
        assertEquals("3360", device.getMaxFrequencyPerCore());
        assertEquals("8", device.getRamSize());
        assertEquals("4266 MHz", device.getRamType());
        assertEquals("1080 x 2340 pixels", device.getDisplayResolution());
        assertEquals(30, device.getDisplayColorAmount());
        assertEquals(3900, device.getBatteryCapacity());
        assertEquals("GPS, GLONASS", device.getGps());
    }
}
