package com.mddb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "MODEL_NAME")
    private String modelName;

    @Column(name = "OPERATING_SYSTEM")
    private String operatingSystem;

    @Column(name = "FORM_FACTOR")
    private String formFactor;

    @Column (name = "WIDTH")
    private String width;

    @Column (name = "HEIGHT")
    private String height;

    @Column (name = "DEPTH")
    private String depth;

    @Column (name = "WEIGHT")
    private String weight;

    @Column (name = "BODY_MATERIAL")
    private String bodyMaterial;

    @Column (name = "BODY_COLOR")
    private String bodyColor;

    @Column (name = "WATER_PROOF")
    private Integer waterProof;

    @Column (name = "DUST_PROOF")
    private Integer dustProof;

    @Column (name = "CRASH_WORTHINESS")
    private Boolean crashWorthiness;

    @Column (name = "PACKAGING")
    private String packaging;

    @Column (name = "SOC")
    private String soc;

//    private Cpu cpu;
    @Column(name = "CPU_NUMBER_OF_CORES")
    private Integer numberOfCores;

    @Column(name = "CPU_ARCHITECTURE")
    private String architecture;

    @Column(name = "CPU_MAX_FREQUENCY_PER_CORE")
    private String maxFrequencyPerCore; //MHZ

    @Column(name = "CPU_INSTRUCTION")
    private String instruction;

    @Column(name = "CPU_MANUFACTURING_METHOD")
    private Integer manufacturingMethod; //nm

//    private GraphicalController graphicalController;
    @Column(name = "GRAPHICAL_CONTROLLER_NAME")
    private String gpuControllerName;

    @Column(name = "GRAPHICAL_CONTROLLER_GPU_CLOCK")
    private Integer gpuClock; // MHZ

//    private Ram ram;
    @Column(name = "RAM_SIZE")
    private String ramSize; //mb 1024

    @Column(name = "RAM_TYPE")
    private String ramType; //LPDDR2

    @Column(name = "RAM_CLOCK")
    private Integer ramClock; //MHZ

    @Column (name = "ROM")
    private String rom;

    @Column (name = "SOUND_CONTROLLER")
    private String soundController;

    @Column (name = "BATTERY_TYPE")
    private String batteryType;

    @Column (name = "BATTERY_CAPACITY")
    private Integer batteryCapacity;

    @Column (name = "GPS")
    private String gps;

    @Column (name = "FM_RECEIVER")
    private Boolean fmReceiver;

    @Column (name = "FM_TRANSMITTER")
    private Boolean fmTransmitter;

    @Column (name = "WIRELESS_CHARGER")
    private String wirelessCharger;

    @Column (name = "ANTENNA")
    private String antenna;

    @Column (name = "VIBRATION")
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private Boolean vibration;

    @Column (name = "LED_NOTIFICATION")
    private String ledNotification;

    @Column (name = "SPEAKER")
    private String speaker;

//    private Display display;
    @Column(name = "DISPLAY_DIAGONAL")
    private String displayDiagonal;

    @Column(name = "DISPLAY_RESOLUTION")
    private String displayResolution;

    @Column(name = "DISPLAY_TYPE")
//    @Enumerated(EnumType.STRING)
    private String displayType;

    @Column(name = "DISPLAY_RATIO")
    private String displayRatio; //по идее должен вычисляться из других параметров

    @Column(name = "DISPLAY_COLOR_AMOUNT")
    private Integer displayColorAmount;

    @Column(name = "DISPLAY_SENSOR_TYPE")
//    @Enumerated(EnumType.STRING)
    private String displaySensorType;

    @Column(name = "DISPLAY_MULTITOUCH")
    private Integer displayMultitouch;

    @Column(name = "DISPLAY_GLASS_DESCRIPTION")
    private String displayGlassDescription;

    @Column(name = "DISPLAY_GLARE_FILTER")
    private Boolean displayGlareFilter;

    @Column(name = "DISPLAY_OLEOPHOBIC")
    private Boolean displayOleophobic;

    public enum displayType {
        LCD, AMOLED
    }

    public enum sensorType {
        RESISTANCE, CAPACITIVE
    }

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
    private Boolean Bluetooth;

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
    private boolean pulsometer;

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

/*    - HSPA+, HSDPA категории 24 (скорость до 42 Мбит/с), HSUPA категории 6 (скорость до 5,8 Мбит/с)
    - LTE диапазоны/band 1, 2, 3, 4, 5, 7, 8, 12, 17, 19, 20, 26, 28, 38, 39, 40, 41 (700, 800, 850, 900, 1700, 1800, 1900, 2100, 2300, 2500, 2600 МГц) 15 категории, скорость до 800 Мбит/с к себе и до неизвестно Мбит/с от себя, MIMO 2x2
    - VoLTE
    - CDMA - не поддерживается
    - одновременная работа в двух сотовых сетях - не поддерживается
    - видеозвонок через сеть сотового оператора - неизвестно
    - Wi-Fi 802.11a, 802.11b, 802.11g, 802.11n, 802.11ac 2.4 ГГц и 5.0 ГГц, скорость до неизвестно Мбит/с, MIMO 2x2
    - Wi-Fi Direct
    - точка доступа Wi-Fi (hotspot)
    - DLNA
    - зеркалирование изображения посредством Wi-Fi miracast (вывод изображения с экрана смартфона на экран телевизора по беспроводному каналу)
    - Google Cast (вывод изображения с экрана смартфона на экран телевизора, находящегося в одной локальной сети со смартфоном)
    - Bluetooth 5.0, поддерживаемые протоколы: apt-X HD остальные неизвестны
    - Bluetooth LE (Bluetooth Low Energy / Bluetooth Smart)
    - ANT+
    - NFC
    - - карты Mifare поддерживаются (чтение и запись карт "тройка" и "стрелка")
    - - Android Pay (Google Wallet, бесконтактная оплата)
    - - оплата с использованием secure element на SIM-карте (например оплата проезда в метро с помощью смартфона)
    - ИК-порт (IRDA) отсутствует
    - MHL проводной видеовыход (через переходник microUSB->HDMI) не поддерживается
    - проводной видеовыход USB типа C на displayport не поддерживается
    - SlimPort видеовыход не поддерживается
    - USB 2.0 (High speed)
    - USB-Host (OTG, поддержка подключения внешних устройств таких как как съемные накопители, клавиатуры, мыши, игровые контроллеры)
    - USB-модем (USB tethering)*/
/*Связь:
- GSM 850/900/1800/1900 МГц
- SMS, MMS
- GPRS сlass 12
- EDGE
- UMTS 850/900/1900/2100 МГц
- HSPA, HSDPA до 42.2 Мбит/с, HSUPA до 5.76 Мбит/с
- LTE 700c/1700f МГЦ (B13, B4) в России работать не будет
- CDMA 800/1900 МГц
- CDMA EV-DO Release A
- видеовызов неизвестно
- Wi-Fi 802.11a,b,g,n 2.4 и 5.0 ГГц
- miracast, вывод изображения с экрана устройства по Wi-Fi на экран телевизора, телевизор должен её поддерживать
- DLNA
- Wi-Fi Direct
- Wi-Fi точка доступа
- Bluetooth Class 2 Version 4.0 LE, EDR, поддерживаемые протоколы: A2DP1.2, AVRCP 1.4, BPP1.2, FTP1.2, HDP 1.0, HFP 1.6+WBS, HID1.0, HSP1.2, MAP1.0, OPP1.2, PAN–NAP1.0, PBAP1.1, SAP1.1, SDP1.1, BLE: HRM
- NFC
- ИК-порт отсутствует

Разъёмы:
- micro USB 2.0 High Speed тип B
- аудиовыход mini-jack (3.5 мм)
- видеовыход отсутствует
- нано-SIM-карта, поддерживается горячая замена, находится под кнопкой громкости
- карта памяти отсутствует

<%--    - автофокус гибридный следящий
    - - фазовый (PDAF)
    - - лазерный
    - вспышка однодиодная
    - разрешение 19 Мп
    - видеосъёмка разрешением 3840 х 2160 точек (4K) 30 кадров/с, разрешением 1920 х 1080 точек (Full HD, 1080p) 60 кадров/с, 1280 х 720 точек (HD, 720p) 960 кадров/с
    - размер матрицы 1/2.3 дюйма, Exmor RS
    - объектив 25 мм широкоугольный Sony G Lens с диафрагмой f/2,0
    - цифровая стабилизация 5-осевая SteadyShot
    - оптическая стабилизация не поддерживается
    - материал, которым накрыт объектив: стекло
    - модель Sony IMX400
    - добавление к фотографиям данных о местоположении (Geo-tagging)
    - фокусировка по точке прикосновения (touch focus)
    - панорама
    - макросъёмка
    - распознавание лиц
    - распознавание улыбок
    - устранение эффекта "красных глаз"
    - режим серийной съёмки
    - режим размытия фона
    - режим ускоренной съёмки (замедленное видео)
    - HDR--%>
Фронтальная камера:
<%--    - автофокус фазовый
    - вспышка отсутствует
    - разрешение 8 Мп
    - видеосъёмка разрешением 1920 x 1080 точек (Full HD, 1080p) 30 кадров/с
    - размер матрицы 1/4 дюйма, Exmor R
    - объектив 18-миллиметровый сверхширокоугольный, с углом обзора до 120°
    - цифровая стабилизация 5-осевая SteadyShot--%>

Кнопки:
<%--    механические:
    - вкл/выкл, блокировка
    - двухпозиционная кнопка регулировки громкости "качелька"
    - двухпозиционная кнопка камеры
    программные:
    - "назад"
    - "домой"
    - "переключение приложений"
    других кнопок нет--%>

Датчики:
<%--    - акселерометр
    - гироскоп
    - приближения
    - освещённости
    - магнитного поля (холла)
    - цифровой компас
    - давления (барометр)
    - температуры батареи
    - отпечатков пальцев (дактилоскопический)
    - шагомер
    - game rotation vector
    - geomagnetic rotation vector
    - significant motion detector
    - ультрафиолета отсутствует
    - пульсометр (сердечного ритма) отсутствует
    - ИК-излучения (RGBC-IR) (используется основной камерой для коррекции баланса белого)--%>

Остальные характеристики:
- 3 микрофона с интеллектуальным усилением голоса и фильтрацией шумов
- USB-OTG
- голосовое управление
- активные уведомления*/


    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setBodyMaterial(String bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public void setWaterProof(Integer waterProof) {
        this.waterProof = waterProof;
    }

    public void setDustProof(Integer dustProof) {
        this.dustProof = dustProof;
    }

    public void setCrashWorthiness(Boolean crashWorthiness) {
        this.crashWorthiness = crashWorthiness;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public void setNumberOfCores(Integer numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public void setMaxFrequencyPerCore(String maxFrequencyPerCore) {
        this.maxFrequencyPerCore = maxFrequencyPerCore;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setManufacturingMethod(Integer manufacturingMethod) {
        this.manufacturingMethod = manufacturingMethod;
    }

    public void setGpuControllerName(String gpuControllerName) {
        this.gpuControllerName = gpuControllerName;
    }

    public void setGpuClock(Integer gpuClock) {
        this.gpuClock = gpuClock;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public void setRamClock(Integer ramClock) {
        this.ramClock = ramClock;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public void setSoundController(String soundController) {
        this.soundController = soundController;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public void setFmReceiver(Boolean fmReceiver) {
        this.fmReceiver = fmReceiver;
    }

    public void setFmTransmitter(Boolean fmTransmitter) {
        this.fmTransmitter = fmTransmitter;
    }

    public void setWirelessCharger(String wirelessCharger) {
        this.wirelessCharger = wirelessCharger;
    }

    public void setAntenna(String antenna) {
        this.antenna = antenna;
    }

    public void setVibration(Boolean vibration) {
        this.vibration = vibration;
    }

    public void setLedNotification(String ledNotification) {
        this.ledNotification = ledNotification;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setDisplayDiagonal(String displayDiagonal) {
        this.displayDiagonal = displayDiagonal;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public void setDisplayRatio(String displayRatio) {
        this.displayRatio = displayRatio;
    }

    public void setDisplayColorAmount(Integer displayColorAmount) {
        this.displayColorAmount = displayColorAmount;
    }

    public void setDisplaySensorType(String displaySensorType) {
        this.displaySensorType = displaySensorType;
    }

    public void setDisplayMultitouch(Integer displayMultitouch) {
        this.displayMultitouch = displayMultitouch;
    }

    public void setDisplayGlassDescription(String displayGlassDescription) {
        this.displayGlassDescription = displayGlassDescription;
    }

    public void setDisplayGlareFilter(Boolean displayGlareFilter) {
        this.displayGlareFilter = displayGlareFilter;
    }

    public void setDisplayOleophobic(Boolean displayOleophobic) {
        this.displayOleophobic = displayOleophobic;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    public void setMms(Boolean mms) {
        this.mms = mms;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public void setGsm(Boolean gsm) {
        this.gsm = gsm;
    }

    public void setGprs(Boolean gprs) {
        this.gprs = gprs;
    }

    public void setEdge(Boolean edge) {
        this.edge = edge;
    }

    public void setUmts(Boolean umts) {
        this.umts = umts;
    }

    public void setHspa(Boolean hspa) {
        this.hspa = hspa;
    }

    public void setLte(Boolean lte) {
        this.lte = lte;
    }

    public void setCdma(Boolean cdma) {
        this.cdma = cdma;
    }

    public void setBluetooth(Boolean bluetooth) {
        Bluetooth = bluetooth;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public void setInfrared(Boolean infrared) {
        this.infrared = infrared;
    }

    public void setGyroscope(Boolean gyroscope) {
        this.gyroscope = gyroscope;
    }

    public void setAccelerometer(Boolean accelerometer) {
        this.accelerometer = accelerometer;
    }

    public void setCompass(Boolean compass) {
        this.compass = compass;
    }

    public void setPulsometer(boolean pulsometer) {
        this.pulsometer = pulsometer;
    }

    public void setUltraVioletSensor(Boolean ultraVioletSensor) {
        this.ultraVioletSensor = ultraVioletSensor;
    }

    public void setProximitySensor(Boolean proximitySensor) {
        this.proximitySensor = proximitySensor;
    }

    public void setLuminanceSensor(Boolean luminanceSensor) {
        this.luminanceSensor = luminanceSensor;
    }

    public void setBarometer(Boolean barometer) {
        this.barometer = barometer;
    }

    public void setTemeratureBatterySensor(Boolean temeratureBatterySensor) {
        this.temeratureBatterySensor = temeratureBatterySensor;
    }

    public void setFingerprintSensor(Boolean fingerprintSensor) {
        this.fingerprintSensor = fingerprintSensor;
    }

    public void setPedometerSensor(Boolean pedometerSensor) {
        this.pedometerSensor = pedometerSensor;
    }

    public void setGameRotationVector(Boolean gameRotationVector) {
        this.gameRotationVector = gameRotationVector;
    }

    public void setGeomagneticRotationVector(Boolean geomagneticRotationVector) {
        this.geomagneticRotationVector = geomagneticRotationVector;
    }

    public void setSignificantMotionDetector(Boolean significantMotionDetector) {
        this.significantMotionDetector = significantMotionDetector;
    }

    public void setHollSensor(Boolean hollSensor) {
        this.hollSensor = hollSensor;
    }

    public void setTrs35(Boolean trs35) {
        this.trs35 = trs35;
    }

    public void setVoLte(Boolean voLte) {
        this.voLte = voLte;
    }

    public void setVideoCall(Boolean videoCall) {
        this.videoCall = videoCall;
    }

    public void setWifiDirect(Boolean wifiDirect) {
        this.wifiDirect = wifiDirect;
    }

    public void setWifiHotspot(Boolean wifiHotspot) {
        this.wifiHotspot = wifiHotspot;
    }

    public void setDlna(Boolean dlna) {
        this.dlna = dlna;
    }

    public void setMiracast(Boolean miracast) {
        this.miracast = miracast;
    }

    public void setBluetoothLe(Boolean bluetoothLe) {
        this.bluetoothLe = bluetoothLe;
    }

    public void setAntPlus(Boolean antPlus) {
        this.antPlus = antPlus;
    }

    public void setNfc(Boolean nfc) {
        this.nfc = nfc;
    }

    public void setMilfare(Boolean milfare) {
        this.milfare = milfare;
    }

    public void setNfcPayments(Boolean nfcPayments) {
        this.nfcPayments = nfcPayments;
    }

    public void setSecureelemetPayments(Boolean secureelemetPayments) {
        this.secureelemetPayments = secureelemetPayments;
    }

    public void setIrda(Boolean irda) {
        this.irda = irda;
    }

    public void setMhl(Boolean mhl) {
        this.mhl = mhl;
    }

    public void setVideoExitDisplayPort(Boolean videoExitDisplayPort) {
        this.videoExitDisplayPort = videoExitDisplayPort;
    }

    public void setSlimport(Boolean slimport) {
        this.slimport = slimport;
    }

    public void setUsb(Boolean usb) {
        this.usb = usb;
    }

    public void setUsbHost(Boolean usbHost) {
        this.usbHost = usbHost;
    }

    public void setUsbTethering(Boolean usbTethering) {
        this.usbTethering = usbTethering;
    }
}
