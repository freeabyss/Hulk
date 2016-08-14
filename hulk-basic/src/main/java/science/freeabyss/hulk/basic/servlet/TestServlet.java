package science.freeabyss.hulk.basic.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by abyss on 05/13/16.
 */
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("servletPath:" + req.getServletPath());
        out.println("contextPath:" + req.getContextPath());
        out.println("authType:" + req.getAuthType());
        out.println("mothod:" + req.getMethod());
        out.println("pathInfo:" + req.getPathInfo());
        out.println("pathTranslated:" + req.getPathTranslated());
        out.println("queryString:" + req.getQueryString());
        out.println("remoteUser:" + req.getRemoteUser());
        out.println("requestedSessionId:" + req.getRequestedSessionId());
        out.println("requestURI:" + req.getRequestURI());
        out.println("localName:" + req.getLocalName());
        out.println("characterEncoding:" + req.getCharacterEncoding());
        out.println("localAddr:" + req.getLocalAddr());
        out.println("protocol:" + req.getProtocol());
        out.println("remoteAddr:" + req.getRemoteAddr());
        out.println("remoteHost:" + req.getRemoteHost());
        out.println("scheme:" + req.getScheme());
        out.println("serverName:" + req.getServerName());

        out.println(getClass().getResource("wolverine").toString());
    }
}
