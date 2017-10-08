<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>MDDB</title> <br>

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
<h1>MDDB</h1>


<%--<p>JSTL URL: ${phones}</p>--%>

<input id="search" type="text" name="search" autocomplete="off" value="" placeholder="Search" disabled />
<br>
<br>
<c:if test="${!empty companies}">
    <table>
<%--        <tr>
            <th width="120">Name</th>
            <th width="120">Value</th>
        </tr>--%>
        <c:forEach items="${companies}" var="company">
            <tr>
<%--                <td>${company}</td>--%>
<%--                <td><a name=${company}>...</a></td>--%>
                <td><a href="<c:url value='/phones/${company}'/>">${company} </a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<footer> <br>
    <p id="demo" style="text-align: center"></p>
    <script>
        var date = new Date().getFullYear();
        document.getElementById("demo").innerHTML = date + " Copyright ©MDDB";
    </script>
</footer>
</body>

</html>