package servlets;


import entities.Crusade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StartServlet", urlPatterns = "")
public class DefaultServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        session.setAttribute("crusade", new Crusade());
        req.getRequestDispatcher("jsp_source/default.jsp").forward(req,resp);
    }
}
