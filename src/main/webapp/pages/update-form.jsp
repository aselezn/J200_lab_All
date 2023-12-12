<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create client</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/create_form.css">
</head>
<body>
<form action="update" method="post">
    <a href="view-list" class="button" id="back">Назад</a>
    <main>
        <div class="create-form-container">
            <h1 class="form-title">
                Обновление клиента
            </h1>

            <h3 class="error-message">
                ${requestScope.errorReason}
            </h3>

            <div class="form-fields">
                <!-- Скрытые поля для передачи ID -->
                <input type="hidden" name="client-id" value="${not empty client ? client.id : ''}">
                <input type="hidden" name="address-id" value="${not empty address ? address.id : ''}">
                <div class="form-field">
                    <input type="text" name="clientName" placeholder="ФИО" value="${not empty client ? client.clientName : ''}">
                </div>
                <div class="form-field">
                    <select name="clientType">
                        <option value="Физическое лицо" ${client.type == 'Физическое лицо' ? 'selected' : ''}>Физическое лицо</option>
                        <option value="Юридическое лицо" ${client.type == 'Юридическое лицо' ? 'selected' : ''}>Юридическое лицо</option>
                    </select>
                </div>
                <div class="form-field">
                    <input type="text" name="ip" placeholder="Cетевой адрес(IP)" value="${not empty address ? address.ip : ''}">
                </div>
                <div class="form-field">
                    <input type="text" name="mac" placeholder="Физический адрес устройства (MAC)" value="${not empty address ? address.mac : ''}">
                </div>
                <div class="form-field">
                    <input type="text" name="model" placeholder="Mодель устройства" value="${not empty address ? address.model : ''}">
                </div>
                <div class="form-field">
                    <input type="text" name="address" placeholder="Mесто нахождения" value="${not empty address ? address.address : ''}">
                </div>
                <div class="form-buttons">
                    <button class="button" type="submit" id="add">Обновить</button>
                    <button class="button" type="reset" id="cancel">Отмена</button>
                </div>
            </div>
        </div>

    </main>
</form>
</body>
</html>