package servlets;

import beans.UpdateBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        String clientName = request.getParameter("clientName");
        String clientType = request.getParameter("clientType");
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String address = request.getParameter("address");


        updateBean.createNewClient(request, response, clientName, clientType, ip, mac, address);
        response.sendRedirect("view-list");
    }
}
