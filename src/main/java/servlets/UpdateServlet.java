package servlets;

import beans.UpdateBean;
import entity.AddressEntity;
import entity.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ValidationUtils;

import java.io.IOException;

@WebServlet(name = "update", value = "/update")
public class UpdateServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String clientId = request.getParameter("client-id");
        String addressId = request.getParameter("address-id");

        ClientEntity client = null;
        AddressEntity addressEntity = null;

        if (clientId != null && !clientId.isEmpty()) {
            client = updateBean.findByClientId(Integer.parseInt(clientId));
            request.setAttribute("client", client);

            if (addressId != null && !addressId.isEmpty()) {
                addressEntity = updateBean.findByAddressId(Integer.parseInt(addressId));
                request.setAttribute("addressEntity", addressEntity);
                request.getRequestDispatcher("pages/update.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("pages/add-address.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        boolean isUpdatingClientAndAddress = false;
        String clientId = request.getParameter("client-id");
        String addressId = request.getParameter("address-id");
        String clientName = request.getParameter("clientName");
        String clientType = request.getParameter("clientType");
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String model = request.getParameter("model");
        String address = request.getParameter("address");

        try {
            if (clientId != null && !clientId.isEmpty() && addressId != null && !addressId.isEmpty()) {
                isUpdatingClientAndAddress = true;
                updateBean.validateClient(clientName, clientType);
                updateBean.validateAddress(ip, mac, model, address);
                updateBean.updateClient(clientId, clientName, clientType, addressId, ip, mac, model, address);
            } else {
                updateBean.validateAddress(ip, mac, model, address);
                updateBean.addNewAddress(clientId, ip, mac, model, address);
            }
            response.sendRedirect("view-list");

        } catch (ValidationUtils.ValidationException e) {
            request.setAttribute("errorReason", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            // сохраняем введенные данные
            request.setAttribute("clientName", clientName);
            request.setAttribute("clientType", clientType);
            request.setAttribute("ip", ip);
            request.setAttribute("mac", mac);
            request.setAttribute("model", model);
            request.setAttribute("address", address);

            request.setAttribute("client-id", clientId);
            request.setAttribute("address-id", addressId);

            // возвращаемся на соответствующую страницу
            if (isUpdatingClientAndAddress) {
                request.getRequestDispatcher("pages/update.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("pages/add-address.jsp").forward(request, response);
            }
        }

    }
}
