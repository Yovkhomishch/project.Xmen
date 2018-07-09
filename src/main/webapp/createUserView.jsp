<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 6/14/18
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Create Account</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="doCreateUser">
    <table border="0">
        <tr>
            <td>Login</td>
            <td><input type="text" name="userName" value="${user.userName}" /></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><input type="text" name="gender" value="${user.gender}" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${user.password}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="login">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>
