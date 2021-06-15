<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/components/header.jsp" %>
    </head>
    <jsp:useBean id="login" scope="request" class="javabeans.LoginBean" />
    <jsp:setProperty name="login" property="*" />
    <body>
        <div class="container-md">
            <form  method="POST" class="mb-3" action="login">
                <label for="usuario" class="form-label">Usuario</label>
                <input class="form-control" id="usuario" name="usuario" type="text" />
                <label for="contrasena" class="form-label">Contraseña</label>
                <input class="form-control" id="contrasena" name="contrasena" type="password"/>
                <button class="btn btn-primary mt-3" type="submit">Ingresar</button>

            </form>
            <c:if test="${ not empty result && result == 'success' }">
                <div class="alert alert-success" role="alert">
                    <c:out value="${message}" />
                </div>
            </c:if>
            <c:if test="${ not empty result && result == 'error' }">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${message}" />
                </div>
            </c:if>
        </div>
    </body>
</html>
