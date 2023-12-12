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
        if (clientId != null && !clientId.isEmpty()) {
            client = updateBean.findByClientId(Integer.parseInt(clientId));
        }
        AddressEntity address = null;
        if (addressId != null && !addressId.isEmpty()) {
            address = updateBean.findByAddressId(Integer.parseInt(addressId));
        }

        request.setAttribute("client", client);
        request.setAttribute("address", address);

        request.getRequestDispatcher("pages/update.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String clientId = request.getParameter("client-id");
            String addressId = request.getParameter("address-id");
            String clientName = request.getParameter("clientName");
            String clientType = request.getParameter("clientType");
            String ip = request.getParameter("ip");
            String mac = request.getParameter("mac");
            String model = request.getParameter("model");
            String address = request.getParameter("address");

            updateBean.validateClient(clientName, clientType);
            updateBean.validateAddress(ip, mac, model, address);

            updateBean.updateClient(clientId, clientName, clientType, addressId, ip, mac, model, address);
            response.sendRedirect("view-list");

        } catch (ValidationUtils.ValidationException e) {
            request.setAttribute("errorReason", e.getMessage());
            request.getRequestDispatcher("pages/update.jsp").forward(request, response);
        }


    }
}
