package security;

import jakarta.ejb.EJB;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AuthServlet", value = "/auth")
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthServlet extends HttpServlet implements Filter {

    @EJB
    private AuthBean authBean;

    public void getPages(String filepath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(filepath);
        dispatcher.forward(request, response);
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/style/")) {
            chain.doFilter(request, response);
            return;
        }


        if ("/auth".equals(path)) {
            chain.doFilter(request, response);
            return;
        }


        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            String loginAttribute = (String) session.getAttribute("username");
            String savedSessionId = Objects.toString(authBean.getSessionId(loginAttribute), "");
            String currentSessionId = session.getId();

            if (savedSessionId.equals(currentSessionId)) {
                chain.doFilter(request, response);
                return;
            }
        }

        httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getPages("pages/login.jsp", request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = Objects.toString(request.getParameter("username"), "");
        String password = Objects.toString(request.getParameter("password"), "");
        HttpSession session = request.getSession();

        if(username.equals("admin") && password.equals("admin")){
            request.getSession().setAttribute("username", username);
            authBean.addLoginSession(username, session.getId());
            response.sendRedirect("view-list");
        } else {
            authBean.removeLogin(username);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorReason", "Invalid username or password");
            getPages("pages/login.jsp", request, response);
        }

    }
}
