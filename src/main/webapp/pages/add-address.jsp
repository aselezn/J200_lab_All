<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавление адреса</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/create_form.css">
</head>
<body>
<form action="update" method="post">
    <a href="view-list" class="button" id="back">Назад</a>
    <main>
        <div class="create-form-container">
            <h1 class="form-title">
                Добавить адрес
            </h1>

            <h3 class="error-message">
                ${requestScope.errorReason}
            </h3>

            <div class="form-fields">
                <!-- Скрытые поля для передачи ID -->
                <input type="hidden" name="client-id" value="${not empty requestScope['client-id'] ? requestScope['client-id'] : not empty client ? client.id : ''}">
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
                    <button class="button" type="submit" id="add">Добавить</button>
                    <button class="button" type="reset" id="cancel">Отмена</button>
                </div>
            </div>
        </div>

    </main>
</form>
</body>
</html>