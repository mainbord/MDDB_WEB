package com.mddb.mapper;

import com.mddb.domain.Device;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeviceMapperImplTest {

    private final DeviceMapperImpl mapper = new DeviceMapperImpl();

    @Test
    void mapParamsToDevice_shouldUseDefaultReleaseDate_whenReleasedDateIsNotAvailable() throws Exception {
        Device device = mapper.mapParamsToDevice(
                Map.of(
                        "Brand", "Samsung",
                        "Model", "Galaxy",
                        "Released", "N/A"
                ),
                123
        );

        assertNotNull(device);
        assertEquals(
                new SimpleDateFormat("yyyy MMM", Locale.ENGLISH).parse("1920 jan"),
                device.releaseDate()
        );
    }
}
