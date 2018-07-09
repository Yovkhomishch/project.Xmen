package servlet;

import domain.Product;
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
 * Created by igor on 6/7/18
 *
 * @author Yovkhomishch I.A.
 */
@WebServlet(urlPatterns = {"/editProduct"} )
public class EditProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditProductServlet() { super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        String code = req.getParameter("code");

        Product product = null;
        String errorString = null;

        try{
            product = DBUtils.findProduct(conn,code);
        }catch (SQLException e){
            errorString = e.getMessage();
        }

        if(errorString != null && product == null){
            resp.sendRedirect(req.getServletPath() + "/productList");
            return;
        }

        req.setAttribute("errorString" , errorString);
        req.setAttribute("product", product);

        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/editProductView.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
