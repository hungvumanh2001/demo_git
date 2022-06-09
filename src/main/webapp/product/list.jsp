<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 05/06/2022
  Time: 11:25 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <c:forEach var="i" begin="0" end="${products.size()-1}">
        <h2>${products.get(i).id}, ${products.get(i).name}, ${products.get(i).price}, ${categories.get(i).name}</h2>
    </c:forEach>
</body>
</html>
