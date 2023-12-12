package servlets;

import beans.UpdateBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class DeleteServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clientId = request.getParameter("client-id");
        String addressId = request.getParameter("address-id");

        if(clientId!=null && !clientId.isEmpty()) {
            updateBean.deleteClient(clientId);
        }
        if(addressId!=null && !addressId.isEmpty()) {
            updateBean.deleteAddress(addressId);
        }
        request.getRequestDispatcher("view-list").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
