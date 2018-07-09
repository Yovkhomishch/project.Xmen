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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by igor on 6/14/18
 *
 * @author Yovkhomishch I.A.
 */

@WebServlet(urlPatterns = "/doCreateUser")
public class DoCreateUserServlet extends HttpServlet{


    private static final long serialVersionUID = 1L;



    public DoCreateUserServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String password = req.getParameter("password");

        UserAccount user = new UserAccount(userName, gender, password);




        try {
            DBUtils.insertUser(conn,user);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        req.setAttribute("user", user);


            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher("/createUserView.jsp");
            dispatcher.forward(req, resp);

           // resp.sendRedirect(req.getContextPath() + "userInfo");
        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}


