package utils;

import domain.Product;
import domain.UserAccount;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBUtils {

    public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException{
        UserAccount user = null;

        String sql = "select a.User_Name, a.Password, a.Gender from USER_ACCOUNT a where a.User_Name = ? and a.password = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,userName);
        pstm.setString(2,password);

        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            user = new UserAccount();
            String gender = rs.getString("Gender");
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
              return user;
        }
        return null;
    }

    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
        UserAccount user = null;

        String sql = "select a.User_Name, a.password, a.Gender from USER_ACCOUNT a where a.User_Name = ? ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);


        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            user = new UserAccount();
            String gender = rs.getString("Gender");
            String password = rs.getString("Password");
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

        public static void insertUser (Connection conn, UserAccount user) throws SQLException{
            String sql = "insert into USER_ACCOUNT(USER_NAME,GENDER,PASSWORD) values (?,?,?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getUserName());
            pstm.setString(2,user.getGender());
            pstm.setString(3,user.getPassword());

            pstm.executeUpdate();
        }


    public static List<Product> queryProduct(Connection conn) throws SQLException{

        List<Product> result = new ArrayList<>();

        String sql = "select a.Code, a.Name, a.Price from PRODUCT a";
        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        while(rs.next()){
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");

            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);

            result.add(product);
        }
        return result;
    }

    public static Product findProduct(Connection conn, String code) throws SQLException{

        Product product = null;

        String sql = "select a.Code, a.Name, a.Price from PRODUCT a where a.Code =?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,code);

        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            product = new Product();
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");

            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            return product;
        }
        return null;
    }

    public static void updateProduct (Connection conn, Product product) throws SQLException{

        String sql = "update PRODUCT set Name =?, Price =? where Code=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,product.getName());
        pstm.setFloat(2,product.getPrice());
        pstm.setString(3,product.getCode());

        pstm.executeUpdate();
    }

    public static void insertProduct (Connection conn, Product product) throws SQLException{
        String sql = "insert into PRODUCT(Code,Name,Price) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(2,product.getName());
        pstm.setFloat(3,product.getPrice());
        pstm.setString(1,product.getCode());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn , String code) throws SQLException{
        String sql = "delete from PRODUCT where Code = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,code);

        pstm.executeUpdate();
    }
 }
