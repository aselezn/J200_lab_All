package servlets;

import beans.DemoSAX;
import beans.Transformer;
import dto.ClientAddressDto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

@WebServlet(name = "check-sax", value = "/check-sax")
public class CheckSAX extends HttpServlet {

    @EJB
    private Transformer transformer;
    @EJB
    private DemoSAX demoSAX;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String clientSAXFilter = request.getParameter("clientSAXFilter");

        // Обработка данных и генерация XML
        Path xmlFilePath = transformer.convertToXML();


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        demoSAX.resetClients();
        demoSAX.setFilters(clientSAXFilter);

        // Парсинг XML файла
        saxParser.parse(new File(xmlFilePath + "/Clients.xml"), demoSAX);
        List<ClientAddressDto> clients = demoSAX.getClients();

        if (clients.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Не найдено ни одного пользователя.");
        } else {
            request.setAttribute("clients", clients);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/view-list.jsp");
        dispatcher.forward(request, response);

    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        request.setCharacterEncoding("UTF-8");

        String clientSAXFilter = request.getParameter("clientSAXFilter");

        String encodedFilter = URLEncoder.encode(clientSAXFilter, StandardCharsets.UTF_8);
        response.sendRedirect("check-sax?clientSAXFilter=" + encodedFilter);
    }

//    @SneakyThrows
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        processRequest(request, response);
//
//    }
//
//    @SneakyThrows
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        processRequest(request, response);
//    }
//
    @SneakyThrows
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String clientSAXFilter = request.getParameter("clientSAXFilter");

        // Получение пути к XML-файлу
        Path xmlFilePath = transformer.convertToXML();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        demoSAX.setFilters(clientSAXFilter);
        try {
            // Использование полученного пути для парсинга файла
            saxParser = factory.newSAXParser();
            saxParser.parse(new File(xmlFilePath + "/Clients.xml"), demoSAX);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<ClientAddressDto> clients = demoSAX.getClients();

        if (clients.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Не найдено ни одного пользователя.");
        } else {
            request.setAttribute("clients", clients);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/xml-list.jsp");
            dispatcher.forward(request, response);
        }

    }
}
