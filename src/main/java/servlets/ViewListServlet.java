package servlets;

import beans.SelectBean;
import dto.ClientAddressDto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ViewListServlet", value = "/view-list")
public class ViewListServlet extends HttpServlet {

    @EJB
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String clientNameFilter = request.getParameter("clientNameFilter");
        String addressFilter = request.getParameter("addressFilter");
        String clientTypeFilter = request.getParameter("clientFilter");

        List<ClientAddressDto> clients;
        if (clientNameFilter == null && addressFilter == null && clientTypeFilter == null) {
            clients = selectBean.findAll();
        } else {
            clients = selectBean.filterClients(clientNameFilter, addressFilter, clientTypeFilter);
        }

        request.setAttribute("clients", clients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/view-list.jsp");
        dispatcher.forward(request, response);
    }

}

