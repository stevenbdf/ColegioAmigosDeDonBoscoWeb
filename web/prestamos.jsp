<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prestamos | Colegio Amigos de Don Bosco</title>
        <%@include file="/components/header.jsp" %>
        <c:if test="${prestamoLista == null}">
            <jsp:forward page="/PrestamosController" />
        </c:if>
    </head>
    <c:catch var ="catchException">
        <body>
            <%@include file="/components/navbar.jsp" %>
            <div class="container-fluid ">
                <div class="row mt-3">
                    <%@include file="/components/aside.jsp" %>
                    <div class="col-10">
                        <c:if test="${ not empty result}">
                            <div class="alert ${result == 'exito' ? 'alert-success' : 'alert-danger'} alert-dismissible fade show" role="alert">
                                <c:out value="${message}" />
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <c:if test="${ not empty param.exito }">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                Prestamo realizado correctamente
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <div class="card">
                            <div class="card-body">
                                <h3>Prestamos</h3>
                                <div class="my-3" style="height:1px;background-color: lightgray;"></div>
                                <p>Historial de prestamos </p>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Fecha Prestamo</th>
                                            <th scope="col">Fecha Regreso</th>
                                            <th scope="col">Estado</th>
                                            <th scope="col">Ejemplar</th>
                                            <th scope="col">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${prestamoLista}" var="prestamo">
                                            <tr>
                                                <th scope="row">${prestamo.id}</th>
                                                <td>${prestamo.fechaPrestamo}</td>
                                                <td>${prestamo.fechaRegreso}</td>
                                                <td>${prestamo.estado}</td>
                                                <td>${prestamo.nombreEjemplar}</td>
                                                <td>
                                                    <c:if test="${prestamo.estado == 'PRESTADO'}">
                                                        <a href="${contextPath}/PrestamosController?idPrestamo=${prestamo.id}"
                                                           type="button"
                                                           class="btn btn-outline-success"
                                                           >Marcar como regresado
                                                        </a>   
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </c:catch>
</html>
