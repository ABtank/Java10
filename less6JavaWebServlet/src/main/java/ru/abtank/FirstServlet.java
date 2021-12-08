package ru.abtank;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
//clean install tomcat7:redeploy
@WebServlet( urlPatterns = "/hello")
public class FirstServlet implements Servlet {
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>HelloWorld</h1><br/><h1>Привет Мир!</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
