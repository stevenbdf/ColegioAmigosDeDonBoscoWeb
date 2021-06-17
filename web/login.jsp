<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | Colegio Amigos de Don Bosco</title>
        <%@include file="/components/header.jsp" %>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="card col-10 col-lg-5 px-5 py-4 shadow mt-5">
                    <div class="text-center">
                        <h2>Colegio Amigos de Don Bosco</h2>
                        <img width="50%" class="rounded-circle my-3" src="https://revistavive.com/wp-content/uploads/2020/05/DON-BOSCO-2.png" />       
                    </div>
                    <h3 class="text-center my-3">Iniciar Sesión</h3>
                    <form  method="POST" class="mb-3">
                        <label for="usuario" class="form-label">Usuario</label>
                        <input class="form-control" id="usuario" name="usuario" type="text" />
                        <label for="contrasena" class="form-label">Contraseña</label>
                        <input class="form-control" id="contrasena" name="contrasena" type="password"/>
                        <div class="d-grid gap-2">
                            <button class="flex btn btn-primary mt-3" type="submit">Ingresar</button>
                        </div>
                    </form>
                    <c:if test="${ not empty result && result == 'success' }">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <c:out value="${message}" />
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${ not empty result && result == 'error' }">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <c:out value="${message}" />
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>              
                </div>
            </div>
        </div>
    </body>
</html>
