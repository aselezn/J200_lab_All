package servlets;

import beans.SelectBean;
import dto.ClientAddressDto;
import jakarta.ejb.EJB;
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

    private void getPage(PrintWriter out, HttpServletRequest request) {
        out.println("<html><head><title>View client list</title></head><body>");

        out.println("<form action=\"view-list\" method=\"post\" accept-charset=\"UTF-8\">");
        out.println("<div>");

        out.println("<label for=\"clientNameFilter\">ФИО:</label>");
        out.println("<input type=\"text\" name=\"clientNameFilter\" id=\"clientNameFilter\">");

        out.println("<label for=\"addressFilter\">Адрес:</label>");
        out.println("<input type=\"text\" name=\"addressFilter\" id=\"addressFilter\">");

        out.println("<label for=\"clientFilter\">Тип клиента:</label>");
        out.println("<select name=\"clientFilter\" id=\"clientFilter\">");
        out.println("<option value=\"\"></option>");
        out.println("<option value=\"Физическое лицо\">Физическое лицо</option>");
        out.println("<option value=\"Юридическое лицо\">Юридическое лицо</option>");
        out.println("</select>");

        out.println("<button type=\"submit\">Поиск</button>");
        out.println("</div>");
        out.println("</form>");

        out.println("<br>");
        out.println("<br>");
        out.println("<a href=\"create\" class=\"button\" id=\"back\">Создать нового клиента</a>");
        out.println("<br>");
        out.println("<br>");

        String clientNameFilter = request.getParameter("clientNameFilter");
        String addressFilter = request.getParameter("addressFilter");
        String clientTypeFilter = request.getParameter("clientFilter");

        List<ClientAddressDto> clients;
        if (clientNameFilter == null && addressFilter == null && clientTypeFilter == null) {
            clients = selectBean.findAll();
        } else {
            clients = selectBean.filterClients(clientNameFilter, addressFilter, clientTypeFilter);
        }

        out.println("<table border=\"1px solid black\">");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>ФИО</td>");
        out.println("<td>Тип</td>");
        out.println("<td>Дата добавления</td>");
        out.println("<td>IP</td>");
        out.println("<td>MAC</td>");
        out.println("<td>Модель</td>");
        out.println("<td>Место нахождения</td>");
        out.println("<tr>");
        clients.forEach(client -> {
            out.println("<td>" + client.getId() + "</td>");
            out.println("<td>" + client.getClientName() + "</td>");
            out.println("<td>" + client.getType() + "</td>");
            out.println("<td>" + client.getAdded() + "</td>");
            out.println("<td>" + Objects.toString(client.getIp(), "") + "</td>");
            out.println("<td>" + Objects.toString(client.getMac(), "") + "</td>");
            out.println("<td>" + Objects.toString(client.getModel(), "") + "</td>");
            out.println("<td>" + Objects.toString(client.getClientAddress(), "") + "</td>");
            out.println("<td><a href=\"http://localhost:8080/J200_lab-1.0-SNAPSHOT/update?client-id=" + client.getId() + "\">update</a></td>");
            out.println("<td><a href=\"http://localhost:8080/J200_lab-1.0-SNAPSHOT/delete?client-id=" + client.getId() + "\">remove</a></td>");
            out.println("</tr>");
        });
        out.println("</table>");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        getPage(response.getWriter(), request); }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        getPage(response.getWriter(), request); }
}

