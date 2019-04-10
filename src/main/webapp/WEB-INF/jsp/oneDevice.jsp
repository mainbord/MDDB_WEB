<%--
  Created by IntelliJ IDEA.
  User: mainbord
  Date: 01.10.17
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
<head>
    <title>MDDB</title> <br>
    <a href="<c:url value='../phones/${device.companyName}'/>">Go to ${device.companyName} </a> <br>
    <a href="../index">Go to Start Page </a>
    <style type="text/css">
        .keyname {
            font-weight: 700;
        }

        .firststring {
            background-color: #F2D6B0;
        }

        .head {
            background-color: #F2FFE2;
        }
    </style>
</head>
<body>

<%--<c:if test="${!empty device}">
    <table>
            <tr>
                <td><a href="<c:url value='/device/${device.id}'/>">${device.modelName} </a></td>
            </tr>

    </table>
</c:if>--%>

<h1>${device.companyName } ${device.modelName}</h1>

<table class="tg">
    <th width="300"></th>
    <th width="300"></th>
    <tr class="firststring">
        <td class="keyname">Release date</td>
        <td>${device.releaseDate}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Company name</td>
<%--            <td><label> <strong><spring:message
                    code="label.firstName" /></strong>
            </label></td>--%>
        <td>${device.companyName}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Model name</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Operating system</td>
        <td>${device.operatingSystem}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Form factor</td>
        <td>${device.formFactor}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Dimensions</td>
        <td>${device.height} * ${device.width} * ${device.depth}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Weight</td>
        <td>${device.weight}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Materials</td>
        <td>${device.bodyMaterial}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Color</td>
        <td>${device.bodyColor}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Water proof</td>
        <td>
            <c:choose>
                <c:when test="${device.waterProof == 0}">
                    <c:out default="None" value="false"/>
                </c:when>
                <c:otherwise>
                    <c:out default="None" value="${device.waterProof}"/>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Dust proof</td>
        <td>
            <c:choose>
                <c:when test="${device.dustProof == 0}">
                    <c:out default="None" value="false"/>
                </c:when>
                <c:otherwise>
                    <c:out default="None" value="${device.dustProof}"/>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Crash resistance</td>
        <td>${device.crashWorthiness}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Packaging</td>
        <td>${device.packaging}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">SOC</td>
        <td>${device.soc}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">CPU</td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Architecture</td>
        <td>${device.architecture}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Max frequency per core</td>
        <td>${device.maxFrequencyPerCore}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Instructions</td>
        <td>${device.instruction}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Manufacturing method</td>
        <td>${device.manufacturingMethod}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Number of cores</td>
        <td>${device.numberOfCores}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Graphical Controller</td>
        <td></td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Name</td>
        <td>${device.gpuControllerName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;GPU clock</td>
        <td>${device.gpuClock}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">RAM</td>
        <td>${device.ramSize}, ${device.ramType}, ${device.ramClock}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">ROM</td>
        <td>${device.rom}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Sound controller</td>
        <td>${device.soundController}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Battery</td>
        <td>${device.batteryType}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Satellite navigation</td>
        <td>${device.gps}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">FM receiver</td>
        <td>${device.fmReceiver}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">FM transmitter</td>
        <td>${device.fmTransmitter}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Wireless charging</td>
        <td>${device.wirelessCharger}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Antenna</td>
        <td>${device.antenna}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Vibration</td>
        <td>${device.vibration}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">LED notification</td>
        <td>${device.ledNotification}</td>
    </tr>
    <tr class="firststring">
        <td class="keyname">Speaker</td>
        <td>${device.speaker}</td>
    </tr>
</table>
<br> <br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Display</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Diagonal</td>
        <td>${device.displayDiagonal}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Resolution</td>
        <td>${device.displayResolution}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Type</td>
        <td>${device.displayType}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Ratio</td>
        <td>${device.displayRatio}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Amount of colors</td>
        <td>${device.displayColorAmount}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Sensor type</td>
        <td>${device.displaySensorType}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Multitouch</td>
        <td>${device.displayMultitouch}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Glass</td>
        <td>${device.displayGlassDescription}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Glare filter</td>
        <td>${device.displayGlareFilter}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Oleophobic</td>
        <td>${device.displayOleophobic}</td>
    </tr>
</table>
<br><br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Communication</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;SMS, MMS</td>
        <td>${device.sms} ${device.mms}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;электронная почта (e-mail)</td>
        <td>${device.email}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;GSM</td>
        <td>${device.gsm}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;GPRS</td>
        <td>${device.gprs}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;EDGE</td>
        <td>${device.edge}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;UMTS</td>
        <td>${device.umts}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;HSPA</td>
        <td>${device.hspa}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;LTE</td>
        <td>${device.lte}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;VoLTE</td>
        <td>${device.voLte}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;CDMA</td>
        <td>${device.cdma}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;одновременная работа в двух сотовых сетях</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;видеозвонок через сеть сотового оператора</td>
        <td>${device.videoCall}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Wi-Fi</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Wi-Fi Direct</td>
        <td>${device.wifiDirect}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;точка доступа Wi-Fi (hotspot)</td>
        <td>${device.wifiHotspot}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;DLNA</td>
        <td>${device.dlna}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;miracast</td>
        <td>${device.miracast}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Bluetooth</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Bluetooth LE</td>
        <td>${device.bluetoothLe}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;ANT+</td>
        <td>${device.antPlus}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;NFC</td>
        <td>${device.nfc}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;карты Milfare</td>
        <td>${device.milfare}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Бесконтактная оплата</td>
        <td>${device.nfcPayments}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;оплата с использованием secure element на SIM-карте</td>
        <td>${device.secureelemetPayments}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;ИК-порт (IRDA)</td>
        <td>${device.irda}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;MHL</td>
        <td>${device.mhl}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;проводной видеовыход USB типа C на displayport</td>
        <td>${device.videoExitDisplayPort}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;SlimPort</td>
        <td>${device.slimport}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;USB</td>
        <td>${device.usb}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;USB-Host</td>
        <td>${device.usbHost}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;USB-модем (USB tethering)</td>
        <td>${device.usbTethering}</td>
    </tr>
</table>
<br><br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Connectors</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;USB</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;TRS 3.5mm</td>
        <td>${device.trs35}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;nano-SIM</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;microSD</td>
        <td>${device.modelName}</td>
    </tr>
    <%--    - USB типа C (USB-C), без заглушки
        - аудиовыход mini-jack (3.5 мм), без заглушки
        - под одну нано-SIM-карту, возможности "горячей" замены нет (без выключения смартфона), с заглушкой
        - под карту памяти microSD объёмом до 256 Гбайт с возможностью "горячей" замены, с заглушкой
        других разъёмов нет--%>
</table>
<br><br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Rear Camera</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;автофокус гибридный следящий</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;автофокус фазовый (PDAF)</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;автофокус лазерный</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Вспышка</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;разрешение фото</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;видеосъёмка разрешением</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;размер матрицы</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;объектив</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;цифровая стабилизация</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;оптическая стабилизация</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;материал, которым накрыт объектив</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;модель</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Geo-tagging</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;touch focus</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;панорама</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;макросъёмка</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;распознавание лиц</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;распознавание улыбок</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;устранение эффекта "красных глаз"</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;режим серийной съёмки</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;режим размытия фона</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;режим ускоренной съёмки (замедленное видео)</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;HDR</td>
        <td>${device.modelName}</td>
    </tr>
</table>
<br> <br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Front Camera</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;автофокус</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;вспышка</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;разрешение</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;видеосъёмка</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;матрица</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;объектив</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;цифровая стабилизация</td>
        <td>${device.modelName}</td>
    </tr>
</table>
<br> <br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Buttons</h3></td>
        <td></td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Механические</td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;вкл/выкл, блокировка</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;двухпозиционная кнопка регулировки громкости "качелька"</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Сенсорные</td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;Назад</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;Домой</td>
        <td>${device.modelName}</td>
    </tr>
    <tr class="secondtstring">
        <td class="keyname">Программные</td>
        <td>Нет</td>
    </tr>

</table>
<br> <br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Sensors</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;акселерометр</td>
        <td>${device.accelerometer}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;гироскоп</td>
        <td>${device.gyroscope}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;приближения</td>
        <td>${device.proximitySensor}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;освещённости</td>
        <td>${device.luminanceSensor}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;магнитного поля (холла)</td>
        <td>${device.hollSensor}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;цифровой компас</td>
        <td>${device.compass}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;давления (барометр)</td>
        <td>${device.barometer}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;температуры батареи</td>
        <td>${device.temeratureBatterySensor}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;отпечатков пальцев (дактилоскопический)</td>
        <td>${device.fingerprintSensor}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;шагомер</td>
        <td>${device.pedometerSensor}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;game rotation vector</td>
        <td>${device.gameRotationVector}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;geomagnetic rotation vector</td>
        <td>${device.geomagneticRotationVector}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;significant motion detector</td>
        <td>${device.significantMotionDetector}</td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;ультрафиолета отсутствует</td>
        <td>${device.ultraVioletSensor}</td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;пульсометр (сердечного ритма)</td>
        <td>${device.pulsometer}</td>
    </tr>
</table>
<br> <br>
<table>
    <th width="300"></th>
    <th width="300"></th>
    <tr class="secondtstring">
        <td class="keyname"><h3>Additionally</h3></td>
        <td></td>
    </tr>
    <tr class="firststring">
        <td>&nbsp;-&emsp;</td>
        <td></td>
    </tr>
    <tr class="secondtstring">
        <td>&nbsp;-&emsp;</td>
        <td></td>
    </tr>
</table>
</body>
<footer><br>
    <p id="demo" style="text-align: center"></p>
    <script>
        var date = new Date().getFullYear();
        document.getElementById("demo").innerHTML = date + " Copyright ©MDDB";
    </script>
</footer>
</html>
