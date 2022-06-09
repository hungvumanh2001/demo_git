<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 17/05/2022
  Time: 10:33 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${searchList}" var="customer">
    <tr>
        <td><a href="/customers?action=view&id=${customer.getId()}">${customer.getName()}</a></td>
        <td>${customer.getEmail()}</td>
        <td>${customer.getAddress()}</td>
        <td><a href="/customers?action=edit&id=${customer.getId()}">edit</a></td>
        <td><a href="/customers?action=delete&id=${customer.getId()}">delete</a></td>
    </tr>
</c:forEach>
</body>
</html>
