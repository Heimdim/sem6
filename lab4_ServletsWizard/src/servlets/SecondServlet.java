package servlets;

import entities.Crusade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SecondServlet", urlPatterns = "/secondStep")
public class SecondServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        String button = req.getParameter("button2");
        if (button != null)
        {
            if(button.equals("back"))
            {
                req.getRequestDispatcher("/firstStep").forward(req,resp);
            }
            else
                {
                    String goal = req.getParameter("goal");
                    if (goal != null && goal.length() > 0) {
                        Crusade crusade = (Crusade) session.getAttribute("crusade");
                        crusade.setGoal(goal);
                        System.out.println(goal);
                        session.setAttribute("crusade", crusade);
                }
                req.getRequestDispatcher("/thirdStep").forward(req,resp);
            }
        }
        req.getRequestDispatcher("jsp_source/second.jsp").forward(req, resp);
    }
}
