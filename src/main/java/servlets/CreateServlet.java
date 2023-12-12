package servlets;

import beans.UpdateBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ValidationUtils;

import java.io.IOException;

@WebServlet(name = "create", value = "/create")
public class CreateServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String clientName = request.getParameter("clientName");
            String clientType = request.getParameter("clientType");
            String ip = request.getParameter("ip");
            String mac = request.getParameter("mac");
            String model = request.getParameter("model");
            String address = request.getParameter("address");

            updateBean.validateClient(clientName, clientType);
            updateBean.validateAddress(ip, mac, model, address);

            updateBean.createNewClient(clientName, clientType, ip, model, mac, address);
            response.sendRedirect("view-list");

        } catch (ValidationUtils.ValidationException e) {
            request.setAttribute("errorReason", e.getMessage());
            request.getRequestDispatcher("pages/create.jsp").forward(request, response);
        }

    }
}
