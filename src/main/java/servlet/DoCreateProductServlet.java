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

@WebServlet(urlPatterns = {"/doCreateProduct"})
public class DoCreateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String regex;
    public DoCreateProductServlet() {
        this.regex = "\\w+";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);

        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");

        float price = 0;

        try{

            price = Float.parseFloat(priceStr);

        }catch (Exception e){
            e.printStackTrace();
        }

        Product product = new Product(code, name, price);

        String errorString = null;

        if(code == null || !code.matches(regex)){
            errorString = "Product Code invalid!";
        }

        if(errorString == null){
            try{
                DBUtils.insertProduct(conn,product);
            }catch (SQLException e){
                errorString = e.getMessage();
            }
        }

        req.setAttribute("errorString", errorString);
        req.setAttribute("product", product);

        if(errorString != null){
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher("/createProductView.jsp");
            dispatcher.forward(req,resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "productList");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
