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
        <%@include file="/components/navbar.jsp" %>
        <div class="container-fluid ">
            <div class="row mt-3">
                <div class="col-2">
                    <div class="text-center">
                        <img width="50%" class="rounded-circle my-3" src="https://revistavive.com/wp-content/uploads/2020/05/DON-BOSCO-2.png" />
                    </div>
                    <h5>Idiomas</h5>
                    <div class="card">
                        <div class="card-body d-flex flex-column">
                            <a class="stretched-link mb-3">Español</a>
                            <a class="stretched-link">Ingles</a>
                        </div>
                    </div>
                </div>
                <div class="col-10">
                    <div class="card">
                        <div class="card-body">
                            <h3>Consulta de ejemplares</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
