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

    @Column (name = "DIMENSION")
    private String dimension;

    @Column (name = "WEIGHT")
    private String weight;

    @Column (name = "BODY_MATERIAL")
    private String bodyMaterial;

    @Column (name = "BODY_COLOR")
    private String bodyColor;

    @Column (name = "WATER_PROOF")
    private String waterProof;

    @Column (name = "DUST_PROOF")
    private String dustProof;

    @Column (name = "CRASH_WORTHINESS")
    private String crashWorthiness;

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
    private Integer maxFrequencyPerCore; //MHZ

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
    private Integer ramSize; //mb 1024

    @Column(name = "RAM_TYPE")
    private String ramType; //LPDDR2

    @Column(name = "RAM_CLOCK")
    private Integer ramClock; //MHZ

    @Column (name = "ROM")
    private String rom;

    @Column (name = "SOUND_CONTROLLER")
    private String soundController;

    @Column (name = "BATTERY")
    private String battery;

    @Column (name = "GPS")
    private String gps;

    @Column (name = "FM_RECEIVER")
    private String fmReceiver;

    @Column (name = "FM_TRANSMITTER")
    private String fmTransmitter;

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
    /*

<%--    - SMS, MMS
    - электронная почта (e-mail)
    - GSM 850, 900, 1800, 1900 МГц
    - GPRS, скорость до 107 кбит/с
    - EDGE, скорость до 296 кбит/с
    - UMTS диапазоны/band 1, 2, 4, 5, 6, 8, 9 (800, 850, 900, 1700, 1900, 2100 МГц)
    - HSPA+, HSDPA категории 24 (скорость до 42 Мбит/с), HSUPA категории 6 (скорость до 5,8 Мбит/с)
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
    - USB-модем (USB tethering)--%>
Связь:
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
}
