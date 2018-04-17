package servlets;

import entities.Crusade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ThirdServlet", urlPatterns = "/thirdStep")
public class ThirdServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        String button = req.getParameter("button3");
        if (button != null)
        {
            if(button.equals("back"))
            {
                req.getRequestDispatcher("/secondStep").forward(req,resp);
            }
            else
            {
                String haveIndulgency = req.getParameter("haveIndulgency"), isAve=req.getParameter("isAve"),
                        isDeus=req.getParameter("isDeus");
                System.out.println(haveIndulgency+isAve+isDeus);
                Crusade crusade = (Crusade) session.getAttribute("crusade");
                if (haveIndulgency != null && haveIndulgency.length() > 0)
                    crusade.setHaveIndulgence(haveIndulgency.equals("on"));
                if(isAve!=null&& isAve.length()>0)
                    crusade.setAve(isAve.equals("on"));
                if(isDeus!=null&& isDeus.length()>0)
                    crusade.setDeus(isDeus.equals("on"));
                session.setAttribute("crusade", crusade);
                req.getRequestDispatcher("/finalStep").forward(req,resp);
            }
        }
        req.getRequestDispatcher("jsp_source/third.jsp").forward(req, resp);
    }
}
