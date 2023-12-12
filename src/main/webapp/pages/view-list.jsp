<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View client list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/view-list.css">
</head>
<body>

<form action="view-list" method="post" accept-charset="UTF-8">
    <div>
        <label for="clientNameFilter">ФИО:</label>
        <input type="text" name="clientNameFilter" id="clientNameFilter">

        <label for="addressFilter">Адрес:</label>
        <input type="text" name="addressFilter" id="addressFilter">

        <label for="clientFilter">Тип клиента:</label>
        <select name="clientFilter" id="clientFilter">
            <option value=""></option>
            <option value="Физическое лицо">Физическое лицо</option>
            <option value="Юридическое лицо">Юридическое лицо</option>
        </select>

        <button type="submit" id="search">Поиск</button>
    </div>
</form>

<br><br>
<a href="create" class="button" id="create">Создать клиента</a>
<br><br>

<table border="1px solid black">
    <tr>
        <td>Client ID</td>
        <td>ФИО</td>
        <td>Тип</td>
        <td>Дата добавления</td>
        <td>Address ID</td>
        <td>IP</td>
        <td>MAC</td>
        <td>Модель</td>
        <td>Место нахождения</td>
        <td>Действия</td>
    </tr>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.clientId}</td>
            <td>${client.clientName}</td>
            <td>${client.type}</td>
            <td>${client.added}</td>
            <td>${client.addressId}</td>
            <td>${client.ip}</td>
            <td>${client.mac}</td>
            <td>${client.model}</td>
            <td>${client.clientAddress}</td>
            <td>
                <input type="checkbox" id="menuToggle${client.clientId}" class="menu-toggle">
                <label for="menuToggle${client.clientId}" class="action-button">Выберете действие</label>
                <div class="action-menu">
                    <a href="update?client-id=${client.clientId}&address-id=${client.addressId}" class="menu-button">Обновить</a>
                    <a href="update?client-id=${client.clientId}" class="menu-button">Добавить адресс</a>
                    <a href="delete?address-id=${client.addressId}" class="menu-button">Удалить адресс</a>
                    <a href="delete?client-id=${client.clientId}" class="menu-button">Удалить все</a>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>