package com.mddb.mapper;

import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class DeviceMapperImpl implements DeviceMapper{
    private static final String DEFAULT_RELEASED_DATE = "1920 jan";

    @Override
    public DeviceDto map(Device device) {
        if (device == null) return null;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(device.id());
        deviceDto.setModelName(device.modelName());
        return deviceDto;
    }

    @Override
    @SneakyThrows
    public Device mapParamsToDevice(Map<String, String> params, Integer id) {
        if (isNull(params) || params.isEmpty()) return null;
        String liquidsProtection = params.getOrDefault("Protection from liquids", "");
        String solidProtection = params.getOrDefault("Protection from solid materials", "");

        return new Device(
                null,
                id,
                parseReleasedDate(params.get("Released")),
                params.get("Brand"),
                params.get("Model"),
                params.getOrDefault("Platform", ""),
                null,
                params.getOrDefault("Width", ""),
                params.getOrDefault("Height", ""),
                params.getOrDefault("Depth", ""),
                params.getOrDefault("Mass", ""),
                null,
                null,
                liquidsProtection.length() == 0 ? 0 : Character.isDigit(liquidsProtection.charAt(0)) ? (int) liquidsProtection.charAt(0) : 0,
                solidProtection.length() == 0 ? 0 : Character.isDigit(solidProtection.charAt(0)) ? (int) solidProtection.charAt(0) : 0,
                null,
                null,
                params.getOrDefault("CPU", "").split(",")[0],
                null,
                null,
                params.getOrDefault("CPU Clock", "0").split(" ")[0].replaceAll("/.", ","),
                null,
                null,
                params.getOrDefault("Graphical Controller", ""),
                null,
                (params.getOrDefault("RAM Capacity", "0 0").split(" ")[0]),
                params.getOrDefault("RAM Type", ""),
                null,
                params.getOrDefault("Non-volatile Memory Capacity", ""),
                null,
                params.getOrDefault("Battery", ""),
                Integer.valueOf(params.getOrDefault("Nominal Battery Capacity", "0 0 ").split(" ")[0]),
                params.getOrDefault("Supported GPS protocol(s)", ""),
                !params.getOrDefault("FM Radio Receiver", "").equalsIgnoreCase("No"),
                null,
                null,
                params.getOrDefault("Cellular Antenna", "").equalsIgnoreCase("Internal antenna") ? "internal" : "external",
                null,
                null,
                params.getOrDefault("Loudpeaker(s)", ""),
                params.getOrDefault("Display Diagonal", ""),
                params.getOrDefault("Display Resolution", ""),
                params.getOrDefault("Display Type", "unknown"),
                null,
                Integer.valueOf(params.getOrDefault("Display Color Depth", "0 0").split(" ")[0]),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                !params.getOrDefault("Bluetooth", "").equalsIgnoreCase("no"),
                !params.getOrDefault("Wireless LAN", "").equalsIgnoreCase("no"),
                !params.getOrDefault("Infrared", "").equalsIgnoreCase("no"),
                params.getOrDefault("Built-in gyroscope", "").equalsIgnoreCase("yes"),
                params.getOrDefault("Built-in accelerometer", "").equalsIgnoreCase("yes"),
                params.getOrDefault("Built-in compass", "").equalsIgnoreCase("yes"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private Date parseReleasedDate(String releasedDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM", Locale.ENGLISH);

        if (releasedDate == null || releasedDate.isBlank()
                || releasedDate.equalsIgnoreCase("Cancelled")
                || releasedDate.equalsIgnoreCase("N/A")) {
            return formatter.parse(DEFAULT_RELEASED_DATE);
        }

        try {
            return formatter.parse(releasedDate);
        } catch (ParseException e) {
            return formatter.parse(DEFAULT_RELEASED_DATE);
        }
    }
}
