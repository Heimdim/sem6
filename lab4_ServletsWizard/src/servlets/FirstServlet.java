package servlets;

import entities.Crusade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FirstServlet", urlPatterns = "/firstStep")
public class FirstServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();


        String button = req.getParameter("button1");
        if (button != null)
        {
            if(button.equals("back"))
            {
                req.getRequestDispatcher("/").forward(req,resp);
            }
            else{
                String name = req.getParameter("popeNameField");
                if (name != null && name.length() > 0) {
                    Crusade crusade = (Crusade) session.getAttribute("crusade");
                    crusade.setPopeName(name);
                    System.out.println(name);
                    session.setAttribute("crusade", crusade);
                }
                req.getRequestDispatcher("/secondStep").forward(req,resp);
            }
        }
        req.getRequestDispatcher("jsp_source/first.jsp").forward(req, resp);
    }
}
