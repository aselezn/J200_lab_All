<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация клиента</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/create_form.css">
</head>
<body>
<form action="create" method="post">
    <a href="view-list" class="button" id="back">Назад</a>
    <main>
        <div class="create-form-container">
            <h1 class="form-title">
                Регистрация клиента
            </h1>

            <h3 class="error-message">
                ${requestScope.errorReason}
            </h3>

            <div class="form-fields">
                <div class="form-field">
                    <input type="text" name="clientName" placeholder="ФИО">
                </div>
                <div class="form-field">
                    <select name="clientType">
                        <option value="Тип клиента">Тип клиента</option>
                        <option value="Физическое лицо">Физическое лицо</option>
                        <option value="Юридическое лицо">Юридическое лицо</option>
                    </select>
                </div>
                <div class="form-field">
                    <input type="text" name="ip" placeholder="Cетевой адрес(IP)">
                </div>
                <div class="form-field">
                    <input type="text" name="mac" placeholder="Физический адрес устройства (MAC)">
                </div>
                <div class="form-field">
                    <input type="text" name="model" placeholder="Mодель устройства">
                </div>
                <div class="form-field">
                    <input type="text" name="address" placeholder="Mесто нахождения">
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
