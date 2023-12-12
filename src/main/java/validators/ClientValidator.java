package validators;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class ClientValidator {

    public static boolean validate(HttpServletRequest request, HttpServletResponse response,
                                   String clientName, String clientType) throws ServletException, IOException {
        validateClientName(request, response, clientName);
        validateClientType(request, response, clientType);
        return true;
    }

    private static void validateClientName(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String clientName) throws ServletException, IOException {
        if(clientName == null || clientName.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Поля не должны быть пустыми");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }
        else if (clientName.trim().length() > 100) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "ФИО не должно превышать 100 символов");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }
        else if (!clientName.matches("[а-яА-ЯёЁ\\- ,.]+")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "ФИО может содержать только русские буквы и символы [- , .]");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }
    }

    private static void validateClientType (HttpServletRequest request,
                                     HttpServletResponse response,
                                     String clientType) throws ServletException, IOException {
        if(!clientType.equals("Юридическое лицо") && !clientType.equals("Физическое лицо")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Тип клиента имеет недопустимое значение");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }

    }


}
