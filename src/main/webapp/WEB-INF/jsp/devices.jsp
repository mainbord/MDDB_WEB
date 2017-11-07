<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>MDDB</title> <br>
    <a href="../index">Go to Start Page </a>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>Devices</h1>


<%--<p>JSTL URL: ${phones}</p>--%>

<c:if test="${!empty devices}">
    <table>
            <%--        <tr>
                        <th width="120">Name</th>
                        <th width="120">Value</th>
                    </tr>--%>
        <c:forEach items="${devices}" var="device">
            <tr>
                    <%--                <td>${company}</td>--%>
                    <%--                <td><a name=${company}>...</a></td>--%>
<%--                <td><a href="<c:url value='/device/${device.id}'/>">${device.modelName} </a></td>--%>
                <td><a href="<c:url value='/device/${device.getKey()}'/>">${device.getValue()}</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<%--<input id="search" type="text" name="search" autocomplete="off" value="" placeholder="Search" disabled />

<c:if test="${!empty phones}">
    <table class="tg">
&lt;%&ndash;        <tr>
            <th width="120">Name</th>
            <th width="120">Value</th>
        </tr>&ndash;%&gt;
        <c:forEach items="${phones}" var="phone">
            <tr>
                <td>Model name</td>
                <td>${phone.modelName}</td>
            </tr>
            <tr>
                <td>SOC</td>
                <td>${phone.soc}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>


---------------
<table style="width : 98%; margin : 2px;">
    <tr>
        <td colspan="2" style="background-color : #FFFFFF"><br/><h4><img style="padding : 0 3px;"
                                                                         title="General Attributes"
                                                                         alt="General Attributes"
                                                                         src="icons/16x16/smartphone.gif">General
            Attributes:&nbsp;</h4></td>
    </tr>
    <tr>
        <td style="background-color : #F6F6E5"><strong>Brand</strong><img
                title="Brand name of the device. This may match with the manufacturer"
                alt="Brand name of the device. This may match with the manufacturer" src="icons/10x10/info_gray.gif">
        </td>
        <td style="background-color : #F6F6E5"><a id="datasheet_item_id1"></a><a
                href="index.php?m=device&s=query&d=detailed_specs&brand=Motorola"
                title="Browse devices whose brand is Motorola">Motorola</a></td>
    </tr>
    <tr>
        <td style="background-color : #FFFFFF"><strong>Model</strong><img
                title="One or more (separated by /) specific model name identifies this device"
                alt="One or more (separated by /) specific model name identifies this device"
                src="icons/10x10/info_gray.gif"></td>
        <td style="background-color : #FFFFFF"><a id="datasheet_item_id2"></a>DROID MINI XT1030</td>
    </tr>
    <tr>
        <td style="background-color : #F6F6E5"><strong>Released</strong><img title="Official release date"
                                                                             alt="Official release date"
                                                                             src="icons/10x10/info_gray.gif"></td>
        <td style="background-color : #F6F6E5"><a id="datasheet_item_id10"></a>2013 Aug</td>
    </tr>
    <tr>
        <td style="background-color : #FFFFFF"><strong>Hardware Designer</strong><img
                title="The company which designed this device" alt="The company which designed this device"
                src="icons/10x10/info_gray.gif"></td>
        <td style="background-color : #FFFFFF"><a id="datasheet_item_id4"></a><a
                href="index.php?m=device&s=query&d=detailed_specs&design=Motorola_Mobile_Devices"
                title="Browse devices whose hardware designer is Motorola Mobile Devices">Motorola Mobile Devices</a>
        </td>
    </tr>
    <tr>
        <td style="background-color : #F6F6E5"><strong>Codename</strong><img
                title="The codename internally identified the design project of this device"
                alt="The codename internally identified the design project of this device"
                src="icons/10x10/info_gray.gif"></td>
        <td style="background-color : #F6F6E5"><a id="datasheet_item_id6"></a><a
                href="index.php?m=device&s=query&d=detailed_specs&codename=Motorola_Obake_Mini"
                title="Browse devices whose codename is Motorola Obake Mini">Motorola Obake Mini</a></td>
    </tr>
    <tr>
        <td style="background-color : #FFFFFF"><strong>OEM ID</strong><img
                title="The OEM ID is internal code and refers to a specific variant of the model series"
                alt="The OEM ID is internal code and refers to a specific variant of the model series"
                src="icons/10x10/info_gray.gif"></td>
        <td style="background-color : #FFFFFF"><a id="datasheet_item_id7"></a><a
                href="index.php?m=device&s=query&d=detailed_specs&oemid=IHDT56PE1"
                title="Browse devices whose oem id is IHDT56PE1">IHDT56PE1</a></td>
    </tr>
    <tr>
        <td style="background-color : #F6F6E5"><strong>Device Category</strong><img
                title="Smartphone, tablet, smart watch, PDA, palmtop, etc."
                alt="Smartphone, tablet, smart watch, PDA, palmtop, etc." src="icons/10x10/info_gray.gif">&nbsp;</td>
        <td style="background-color : #F6F6E5"><a id="datasheet_item_id28"></a><a
                title="Browse devices having Device Category Smartphone"
                href="index.php?m=device&s=query&d=detailed_specs&cat=131">Smartphone</a><img
                title="Callular voice-capable usually touchscreen mobile computer which natively runs third-party apps."
                alt="Callular voice-capable usually touchscreen mobile computer which natively runs third-party apps."
                src="icons/10x10/info_gray.gif"></td>
    </tr>
    <tr>
        <td style="background-color : #FFFFFF"><strong>Market Countries</strong><img
                title="Countries where the device was officially sold. Overlap is possible."
                alt="Countries where the device was officially sold. Overlap is possible."
                src="icons/10x10/info_gray.gif">&nbsp;</td>
        <td style="background-color : #FFFFFF"><a id="datasheet_item_id494"></a><a
                title="Browse devices having Market Countries USA"
                href="index.php?m=device&s=query&d=detailed_specs&country=156">USA</a></td>
    </tr>
</table>--%>
<footer> <br>
    <p id="demo" style="text-align: center"></p>
    <script>
        var date = new Date().getFullYear();
        document.getElementById("demo").innerHTML = (date + " Copyright ©MDDB");
    </script>
</footer>
</body>

</html>