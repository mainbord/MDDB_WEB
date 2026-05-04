package com.mddb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_PHONEDB")
    private Integer idPhonedb;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "MODEL_NAME")
    private String modelName;

    @Column(name = "OPERATING_SYSTEM")
    private String operatingSystem;

    @Column(name = "FORM_FACTOR")
    private String formFactor;

    @Column(name = "WIDTH")
    private String width;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "DEPTH")
    private String depth;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "BODY_MATERIAL")
    private String bodyMaterial;

    @Column(name = "BODY_COLOR")
    private String bodyColor;

    @Column(name = "WATER_PROOF")
    private Integer waterProof;

    @Column(name = "DUST_PROOF")
    private Integer dustProof;

    @Column(name = "CRASH_WORTHINESS")
    private Boolean crashWorthiness;

    @Column(name = "PACKAGING")
    private String packaging;

    @Column(name = "SOC")
    private String soc;

    @Column(name = "CPU_NUMBER_OF_CORES")
    private Integer numberOfCores;

    @Column(name = "CPU_ARCHITECTURE")
    private String architecture;

    @Column(name = "CPU_MAX_FREQUENCY_PER_CORE")
    private String maxFrequencyPerCore;

    @Column(name = "CPU_INSTRUCTION")
    private String instruction;

    @Column(name = "CPU_MANUFACTURING_METHOD")
    private Integer manufacturingMethod;

    @Column(name = "GRAPHICAL_CONTROLLER_NAME")
    private String gpuControllerName;

    @Column(name = "GRAPHICAL_CONTROLLER_GPU_CLOCK")
    private Integer gpuClock;

    @Column(name = "RAM_SIZE")
    private String ramSize;

    @Column(name = "RAM_TYPE")
    private String ramType;

    @Column(name = "RAM_CLOCK")
    private Integer ramClock;

    @Column(name = "ROM")
    private String rom;

    @Column(name = "SOUND_CONTROLLER")
    private String soundController;

    @Column(name = "BATTERY_TYPE")
    private String batteryType;

    @Column(name = "BATTERY_CAPACITY")
    private Integer batteryCapacity;

    @Column(name = "GPS")
    private String gps;

    @Column(name = "FM_RECEIVER")
    private Boolean fmReceiver;

    @Column(name = "FM_TRANSMITTER")
    private Boolean fmTransmitter;

    @Column(name = "WIRELESS_CHARGER")
    private String wirelessCharger;

    @Column(name = "ANTENNA")
    private String antenna;

    @Column(name = "VIBRATION")
    private Boolean vibration;

    @Column(name = "LED_NOTIFICATION")
    private String ledNotification;

    @Column(name = "SPEAKER")
    private String speaker;

    @Column(name = "DISPLAY_DIAGONAL")
    private String displayDiagonal;

    @Column(name = "DISPLAY_RESOLUTION")
    private String displayResolution;

    @Column(name = "DISPLAY_TYPE")
    private String displayType;

    @Column(name = "DISPLAY_RATIO")
    private String displayRatio;

    @Column(name = "DISPLAY_COLOR_AMOUNT")
    private Integer displayColorAmount;

    @Column(name = "DISPLAY_SENSOR_TYPE")
    private String displaySensorType;

    @Column(name = "DISPLAY_MULTITOUCH")
    private Integer displayMultitouch;

    @Column(name = "DISPLAY_GLASS_DESCRIPTION")
    private String displayGlassDescription;

    @Column(name = "DISPLAY_GLARE_FILTER")
    private Boolean displayGlareFilter;

    @Column(name = "DISPLAY_OLEOPHOBIC")
    private Boolean displayOleophobic;

    @Column(name = "SMS")
    private Boolean sms;

    @Column(name = "MMS")
    private Boolean mms;

    @Column(name = "EMAIL")
    private Boolean email;

    @Column(name = "GSM")
    private Boolean gsm;

    @Column(name = "GPRS")
    private Boolean gprs;

    @Column(name = "EdGE")
    private Boolean edge;

    @Column(name = "UMTS")
    private Boolean umts;

    @Column(name = "HSPA")
    private Boolean hspa;

    @Column(name = "LTE")
    private Boolean lte;

    @Column(name = "CDMA")
    private Boolean cdma;

    @Column(name = "BLUETOOTH")
    private Boolean bluetooth;

    @Column(name = "WIFI")
    private Boolean wifi;

    @Column(name = "INFRARED")
    private Boolean infrared;

    @Column(name = "GYROSCOPE")
    private Boolean gyroscope;

    @Column(name = "ACCELEROMETER")
    private Boolean accelerometer;

    @Column(name = "COMPASS")
    private Boolean compass;

    @Column(name = "PULSOMETER")
    private Boolean pulsometer;

    @Column(name = "ULTRA_VIOLET_SENSOR")
    private Boolean ultraVioletSensor;

    @Column(name = "PROXIMITY_SENSOR")
    private Boolean proximitySensor;

    @Column(name = "LUMINANCE_SENSOR")
    private Boolean luminanceSensor;

    @Column(name = "BAROMETER")
    private Boolean barometer;

    @Column(name = "TEMERATURE_BATTERY_SENSOR")
    private Boolean temeratureBatterySensor;

    @Column(name = "FINGERPRINT_SENSOR")
    private Boolean fingerprintSensor;

    @Column(name = "PEDOMETER_SENSOR")
    private Boolean pedometerSensor;

    @Column(name = "GAME_ROTATION_VECTOR")
    private Boolean gameRotationVector;

    @Column(name = "GEOMAGNETIC_ROTATION_VECTOR")
    private Boolean geomagneticRotationVector;

    @Column(name = "SIGNIFICANT_MOTION_DETECTOR")
    private Boolean significantMotionDetector;

    @Column(name = "HOLL_SENSOR")
    private Boolean hollSensor;

    @Column(name = "TRS_35")
    private Boolean trs35;

    @Column(name = "VoLTE")
    private Boolean voLte;

    @Column(name = "VIDEO_CALL")
    private Boolean videoCall;

    @Column(name = "WIFI_Direct")
    private Boolean wifiDirect;

    @Column(name = "WIFI_HOTSPOT")
    private Boolean wifiHotspot;

    @Column(name = "DLNA")
    private Boolean dlna;

    @Column(name = "MIRACAST")
    private Boolean miracast;

    @Column(name = "Bluetooth_LE")
    private Boolean bluetoothLe;

    @Column(name = "ANT_PLUS")
    private Boolean antPlus;

    @Column(name = "NFC")
    private Boolean nfc;

    @Column(name = "MILFARE")
    private Boolean milfare;

    @Column(name = "NFCPAYMENTS")
    private Boolean nfcPayments;

    @Column(name = "SECUREELEMET_PAYMENTS")
    private Boolean secureelemetPayments;

    @Column(name = "IRDA")
    private Boolean irda;

    @Column(name = "MHL")
    private Boolean mhl;

    @Column(name = "VIDEO_EXIT_TO_DISPLAY_PORT")
    private Boolean videoExitDisplayPort;

    @Column(name = "SLIMPORT")
    private Boolean slimport;

    @Column(name = "USB")
    private Boolean usb;

    @Column(name = "USB_HOST")
    private Boolean usbHost;

    @Column(name = "USB_TETHERING")
    private Boolean usbTethering;

    public Device() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Device(Integer id, Integer idPhonedb, Date releaseDate, String companyName, String modelName, String operatingSystem, String formFactor, String width, String height, String depth, String weight, String bodyMaterial, String bodyColor, Integer waterProof, Integer dustProof, Boolean crashWorthiness, String packaging, String soc, Integer numberOfCores, String architecture, String maxFrequencyPerCore, String instruction, Integer manufacturingMethod, String gpuControllerName, Integer gpuClock, String ramSize, String ramType, Integer ramClock, String rom, String soundController, String batteryType, Integer batteryCapacity, String gps, Boolean fmReceiver, Boolean fmTransmitter, String wirelessCharger, String antenna, Boolean vibration, String ledNotification, String speaker, String displayDiagonal, String displayResolution, String displayType, String displayRatio, Integer displayColorAmount, String displaySensorType, Integer displayMultitouch, String displayGlassDescription, Boolean displayGlareFilter, Boolean displayOleophobic, Boolean sms, Boolean mms, Boolean email, Boolean gsm, Boolean gprs, Boolean edge, Boolean umts, Boolean hspa, Boolean lte, Boolean cdma, Boolean bluetooth, Boolean wifi, Boolean infrared, Boolean gyroscope, Boolean accelerometer, Boolean compass, Boolean pulsometer, Boolean ultraVioletSensor, Boolean proximitySensor, Boolean luminanceSensor, Boolean barometer, Boolean temeratureBatterySensor, Boolean fingerprintSensor, Boolean pedometerSensor, Boolean gameRotationVector, Boolean geomagneticRotationVector, Boolean significantMotionDetector, Boolean hollSensor, Boolean trs35, Boolean voLte, Boolean videoCall, Boolean wifiDirect, Boolean wifiHotspot, Boolean dlna, Boolean miracast, Boolean bluetoothLe, Boolean antPlus, Boolean nfc, Boolean milfare, Boolean nfcPayments, Boolean secureelemetPayments, Boolean irda, Boolean mhl, Boolean videoExitDisplayPort, Boolean slimport, Boolean usb, Boolean usbHost, Boolean usbTethering) {
        this.id = id;
        this.idPhonedb = idPhonedb;
        this.releaseDate = releaseDate;
        this.companyName = companyName;
        this.modelName = modelName;
        this.operatingSystem = operatingSystem;
        this.formFactor = formFactor;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
        this.bodyMaterial = bodyMaterial;
        this.bodyColor = bodyColor;
        this.waterProof = waterProof;
        this.dustProof = dustProof;
        this.crashWorthiness = crashWorthiness;
        this.packaging = packaging;
        this.soc = soc;
        this.numberOfCores = numberOfCores;
        this.architecture = architecture;
        this.maxFrequencyPerCore = maxFrequencyPerCore;
        this.instruction = instruction;
        this.manufacturingMethod = manufacturingMethod;
        this.gpuControllerName = gpuControllerName;
        this.gpuClock = gpuClock;
        this.ramSize = ramSize;
        this.ramType = ramType;
        this.ramClock = ramClock;
        this.rom = rom;
        this.soundController = soundController;
        this.batteryType = batteryType;
        this.batteryCapacity = batteryCapacity;
        this.gps = gps;
        this.fmReceiver = fmReceiver;
        this.fmTransmitter = fmTransmitter;
        this.wirelessCharger = wirelessCharger;
        this.antenna = antenna;
        this.vibration = vibration;
        this.ledNotification = ledNotification;
        this.speaker = speaker;
        this.displayDiagonal = displayDiagonal;
        this.displayResolution = displayResolution;
        this.displayType = displayType;
        this.displayRatio = displayRatio;
        this.displayColorAmount = displayColorAmount;
        this.displaySensorType = displaySensorType;
        this.displayMultitouch = displayMultitouch;
        this.displayGlassDescription = displayGlassDescription;
        this.displayGlareFilter = displayGlareFilter;
        this.displayOleophobic = displayOleophobic;
        this.sms = sms;
        this.mms = mms;
        this.email = email;
        this.gsm = gsm;
        this.gprs = gprs;
        this.edge = edge;
        this.umts = umts;
        this.hspa = hspa;
        this.lte = lte;
        this.cdma = cdma;
        this.bluetooth = bluetooth;
        this.wifi = wifi;
        this.infrared = infrared;
        this.gyroscope = gyroscope;
        this.accelerometer = accelerometer;
        this.compass = compass;
        this.pulsometer = pulsometer;
        this.ultraVioletSensor = ultraVioletSensor;
        this.proximitySensor = proximitySensor;
        this.luminanceSensor = luminanceSensor;
        this.barometer = barometer;
        this.temeratureBatterySensor = temeratureBatterySensor;
        this.fingerprintSensor = fingerprintSensor;
        this.pedometerSensor = pedometerSensor;
        this.gameRotationVector = gameRotationVector;
        this.geomagneticRotationVector = geomagneticRotationVector;
        this.significantMotionDetector = significantMotionDetector;
        this.hollSensor = hollSensor;
        this.trs35 = trs35;
        this.voLte = voLte;
        this.videoCall = videoCall;
        this.wifiDirect = wifiDirect;
        this.wifiHotspot = wifiHotspot;
        this.dlna = dlna;
        this.miracast = miracast;
        this.bluetoothLe = bluetoothLe;
        this.antPlus = antPlus;
        this.nfc = nfc;
        this.milfare = milfare;
        this.nfcPayments = nfcPayments;
        this.secureelemetPayments = secureelemetPayments;
        this.irda = irda;
        this.mhl = mhl;
        this.videoExitDisplayPort = videoExitDisplayPort;
        this.slimport = slimport;
        this.usb = usb;
        this.usbHost = usbHost;
        this.usbTethering = usbTethering;
    }

    public Integer id() {
        return id;
    }

    public Integer idPhonedb() {
        return idPhonedb;
    }

    public Date releaseDate() {
        return releaseDate;
    }

    public String companyName() {
        return companyName;
    }

    public String modelName() {
        return modelName;
    }

    public String operatingSystem() {
        return operatingSystem;
    }

    public String formFactor() {
        return formFactor;
    }

    public String width() {
        return width;
    }

    public String height() {
        return height;
    }

    public String depth() {
        return depth;
    }

    public String weight() {
        return weight;
    }

    public String bodyMaterial() {
        return bodyMaterial;
    }

    public String bodyColor() {
        return bodyColor;
    }

    public Integer waterProof() {
        return waterProof;
    }

    public Integer dustProof() {
        return dustProof;
    }

    public Boolean crashWorthiness() {
        return crashWorthiness;
    }

    public String packaging() {
        return packaging;
    }

    public String soc() {
        return soc;
    }

    public Integer numberOfCores() {
        return numberOfCores;
    }

    public String architecture() {
        return architecture;
    }

    public String maxFrequencyPerCore() {
        return maxFrequencyPerCore;
    }

    public String instruction() {
        return instruction;
    }

    public Integer manufacturingMethod() {
        return manufacturingMethod;
    }

    public String gpuControllerName() {
        return gpuControllerName;
    }

    public Integer gpuClock() {
        return gpuClock;
    }

    public String ramSize() {
        return ramSize;
    }

    public String ramType() {
        return ramType;
    }

    public Integer ramClock() {
        return ramClock;
    }

    public String rom() {
        return rom;
    }

    public String soundController() {
        return soundController;
    }

    public String batteryType() {
        return batteryType;
    }

    public Integer batteryCapacity() {
        return batteryCapacity;
    }

    public String gps() {
        return gps;
    }

    public Boolean fmReceiver() {
        return fmReceiver;
    }

    public Boolean fmTransmitter() {
        return fmTransmitter;
    }

    public String wirelessCharger() {
        return wirelessCharger;
    }

    public String antenna() {
        return antenna;
    }

    public Boolean vibration() {
        return vibration;
    }

    public String ledNotification() {
        return ledNotification;
    }

    public String speaker() {
        return speaker;
    }

    public String displayDiagonal() {
        return displayDiagonal;
    }

    public String displayResolution() {
        return displayResolution;
    }

    public String displayType() {
        return displayType;
    }

    public String displayRatio() {
        return displayRatio;
    }

    public Integer displayColorAmount() {
        return displayColorAmount;
    }

    public String displaySensorType() {
        return displaySensorType;
    }

    public Integer displayMultitouch() {
        return displayMultitouch;
    }

    public String displayGlassDescription() {
        return displayGlassDescription;
    }

    public Boolean displayGlareFilter() {
        return displayGlareFilter;
    }

    public Boolean displayOleophobic() {
        return displayOleophobic;
    }

    public Boolean sms() {
        return sms;
    }

    public Boolean mms() {
        return mms;
    }

    public Boolean email() {
        return email;
    }

    public Boolean gsm() {
        return gsm;
    }

    public Boolean gprs() {
        return gprs;
    }

    public Boolean edge() {
        return edge;
    }

    public Boolean umts() {
        return umts;
    }

    public Boolean hspa() {
        return hspa;
    }

    public Boolean lte() {
        return lte;
    }

    public Boolean cdma() {
        return cdma;
    }

    public Boolean bluetooth() {
        return bluetooth;
    }

    public Boolean wifi() {
        return wifi;
    }

    public Boolean infrared() {
        return infrared;
    }

    public Boolean gyroscope() {
        return gyroscope;
    }

    public Boolean accelerometer() {
        return accelerometer;
    }

    public Boolean compass() {
        return compass;
    }

    public Boolean pulsometer() {
        return pulsometer;
    }

    public Boolean ultraVioletSensor() {
        return ultraVioletSensor;
    }

    public Boolean proximitySensor() {
        return proximitySensor;
    }

    public Boolean luminanceSensor() {
        return luminanceSensor;
    }

    public Boolean barometer() {
        return barometer;
    }

    public Boolean temeratureBatterySensor() {
        return temeratureBatterySensor;
    }

    public Boolean fingerprintSensor() {
        return fingerprintSensor;
    }

    public Boolean pedometerSensor() {
        return pedometerSensor;
    }

    public Boolean gameRotationVector() {
        return gameRotationVector;
    }

    public Boolean geomagneticRotationVector() {
        return geomagneticRotationVector;
    }

    public Boolean significantMotionDetector() {
        return significantMotionDetector;
    }

    public Boolean hollSensor() {
        return hollSensor;
    }

    public Boolean trs35() {
        return trs35;
    }

    public Boolean voLte() {
        return voLte;
    }

    public Boolean videoCall() {
        return videoCall;
    }

    public Boolean wifiDirect() {
        return wifiDirect;
    }

    public Boolean wifiHotspot() {
        return wifiHotspot;
    }

    public Boolean dlna() {
        return dlna;
    }

    public Boolean miracast() {
        return miracast;
    }

    public Boolean bluetoothLe() {
        return bluetoothLe;
    }

    public Boolean antPlus() {
        return antPlus;
    }

    public Boolean nfc() {
        return nfc;
    }

    public Boolean milfare() {
        return milfare;
    }

    public Boolean nfcPayments() {
        return nfcPayments;
    }

    public Boolean secureelemetPayments() {
        return secureelemetPayments;
    }

    public Boolean irda() {
        return irda;
    }

    public Boolean mhl() {
        return mhl;
    }

    public Boolean videoExitDisplayPort() {
        return videoExitDisplayPort;
    }

    public Boolean slimport() {
        return slimport;
    }

    public Boolean usb() {
        return usb;
    }

    public Boolean usbHost() {
        return usbHost;
    }

    public Boolean usbTethering() {
        return usbTethering;
    }

    public enum DisplayType {
        LCD, AMOLED
    }

    public enum SensorType {
        RESISTANCE, CAPACITIVE
    }
}
