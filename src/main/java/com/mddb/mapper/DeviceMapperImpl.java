package com.mddb.mapper;

import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class DeviceMapperImpl implements DeviceMapper{
    @Override
    public DeviceDto map(Device device) {
        if (device == null) return null;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(device.getId());
        deviceDto.setModelName(device.getModelName());
        return deviceDto;
    }

    @Override
    @SneakyThrows
    public Device mapParamsToDevice(Map<String, String> params, Integer id) {
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
        device.setIdPhonedb(id);
        device.setGps(params.getOrDefault("Supported GPS protocol(s)", ""));
        return device;
    }
}
