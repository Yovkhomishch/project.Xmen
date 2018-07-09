package servlet;

import domain.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/doLogin"})
public class DoLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DoLoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
       String rememberMeStr = request.getParameter("rememberMe");

       boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if(userName == null || password == null || userName.length() == 0 || password.length() == 0){
            hasError = true;
            errorString = "Required user name and password!";
        }else{
            Connection conn = MyUtils.getStoredConnection(request);

            try{
                user = DBUtils.findUser(conn, userName, password);

                if(user == null){
                    hasError = true;
                    errorString = "User Name or password invalid!";
                }

            }catch (SQLException e){
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if(hasError){
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user" , user);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/loginView.jsp");
            dispatcher.forward(request,response);
        }else {
            HttpSession session = request.getSession();
            if(remember){
                MyUtils.storeLoginedUser(session, user);
            } else {
                MyUtils.deleteUserCookie(response);
            }

               response.sendRedirect(request.getContextPath()+ "/userInfo");
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
