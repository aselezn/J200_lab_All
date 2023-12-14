<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Обновление клиента</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/create_form.css">
</head>
<body>
<form action="update" method="post">
    <a href="view-list" class="button" id="back">Назад</a>
    <main>
        <div class="create-form-container">
            <h1 class="form-title">Обновление клиента</h1>

            <h3 class="error-message">${requestScope.errorReason}</h3>

            <div class="form-fields">
                <!-- Скрытые поля для передачи ID -->
                <input type="hidden" name="client-id" value="${not empty requestScope['client-id'] ? requestScope['client-id'] : not empty client ? client.id : ''}">
                <input type="hidden" name="address-id" value="${not empty requestScope['address-id'] ? requestScope['address-id'] : not empty addressEntity ? addressEntity.id : ''}">

                <div class="form-field">
                    <input type="text" name="clientName" placeholder="ФИО" value="${not empty requestScope.clientName ? requestScope.clientName : not empty client ? client.clientName : ''}">
                </div>

                <div class="form-field">
                    <select name="clientType">
                        <option value="Физическое лицо" ${not empty requestScope.clientType ? requestScope.clientType == 'Физическое лицо' : client.type == 'Физическое лицо' ? 'selected' : ''}>Физическое лицо</option>
                        <option value="Юридическое лицо" ${not empty requestScope.clientType ? requestScope.clientType == 'Юридическое лицо' : client.type == 'Юридическое лицо' ? 'selected' : ''}>Юридическое лицо</option>
                    </select>
                </div>

                <div class="form-field">
                    <input type="text" name="ip" placeholder="Cетевой адрес(IP)" value="${not empty requestScope.ip ? requestScope.ip : not empty addressEntity ? addressEntity.ip : ''}">
                </div>

                <div class="form-field">
                    <input type="text" name="mac" placeholder="Физический адрес устройства (MAC)" value="${not empty requestScope.mac ? requestScope.mac : not empty addressEntity ? addressEntity.mac : ''}">
                </div>

                <div class="form-field">
                    <input type="text" name="model" placeholder="Mодель устройства" value="${not empty requestScope.model ? requestScope.model : not empty addressEntity ? addressEntity.model : ''}">
                </div>

                <div class="form-field">
                    <input type="text" name="address" placeholder="Mесто нахождения" value="${not empty requestScope.address ? requestScope.address : not empty addressEntity ? addressEntity.address : ''}">
                </div>

                <div class="form-buttons">
                    <button class="button" type="submit" id="add">Обновить</button>
                </div>
            </div>
        </div>

    </main>
</form>
</body>
</html>
