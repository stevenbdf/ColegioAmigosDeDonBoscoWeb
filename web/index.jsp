<%-- 
    Document   : index
    Created on : Jun 15, 2021, 11:35:06 PM
    Author     : elaniin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Colegio Amigos de Don Bosco</title>
        <%@include file="/components/header.jsp" %>
    </head>
    <body>
        <h1>Bienvenido ${sessionScope.usuario} a Colegio Amigos de Don Bosco </h1>
    </body>
</html>
