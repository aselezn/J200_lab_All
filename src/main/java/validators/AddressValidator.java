package validators;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class AddressValidator {

    public static boolean validate(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String ipAddress,
                                   String macAddress,
                                   String address) throws ServletException, IOException {
        validateIp(request, response, ipAddress);
        validateMac(request, response, macAddress);
        validateAddress(request, response, address);
        return true;
    }

    private static void validateIp(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String ipAddress) throws ServletException, IOException {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Поле IP-адреса не должно быть пустым");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
            return;
        }

        String ipRegex = "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";

        if (!ipAddress.matches(ipRegex)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Недопустимый формат IP-адреса");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }

    }

    private static void validateMac(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String macAddress) throws ServletException, IOException {
        if (macAddress == null || macAddress.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Поле MAC-адреса не должно быть пустым");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
            return;
        }

        String macRegex = "^([0-9A-Fa-f]{1,2}[:-]){5}([0-9A-Fa-f]{1,2})$";

        if (!macAddress.matches(macRegex)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Недопустимый формат MAC-адреса");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }

    }

    private static void validateAddress(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String address) throws ServletException, IOException {
        if (address == null || address.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Поле адреса не должно быть пустым");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
            return;
        }

        if (address.length() > 200) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Длина адреса не должна превышать 200 символов");
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }

    }
}
