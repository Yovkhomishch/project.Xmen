package servlet;

import domain.UserAccount;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userInfo"})
public class UserInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserInfoServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();

        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        if(loginedUser == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("user", loginedUser);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userInfoView.jsp");

        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
