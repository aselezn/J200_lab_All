package servlets;

import beans.UpdateBean;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "update", value = "/update")
public class UpdateServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;
}
