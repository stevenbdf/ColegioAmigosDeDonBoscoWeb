<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prestar | Colegio Amigos de Don Bosco</title>
        <%@include file="/components/header.jsp" %>
    </head>
    <c:catch var ="catchException">
        <jsp:useBean id="prestamoForm" scope="request" class="javabeans.PrestamoBean" />
        <jsp:setProperty name="prestamoForm" property="*" />
        <c:if test="${param.id == null || not empty catchException}">
            <c:redirect url="/" />
        </c:if>
        <c:if test="${not empty param.id && ejemplarLista == null}">
            <jsp:forward page="/PrestarController" />
        </c:if>
        <body>
            <%@include file="/components/navbar.jsp" %>
            <div class="container-fluid ">
                <div class="row mt-3">
                    <%@include file="/components/aside.jsp" %>
                    <div class="col-10">
                        <c:if test="${ not empty error && not empty param.idUsuario}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <c:out value="${error}" />
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>   
                        <div class="card">
                            <div class="card-body">
                                <h3>Prestar un ejemplar</h3>
                                <div class="my-3" style="height:1px;background-color: lightgray;"></div>
                                <p>Ingrese los datos de busqueda: </p>
                                <form class="row" method="POST">
                                    <input type="hidden" value="${sessionScope.id}" name="idUsuario" />
                                    <label class="form-label col-6">
                                        Ejemplar
                                        <select class="form-select" name="idEjemplar">
                                            <option value="0">Selecciona una opcion</option>
                                            <c:forEach items="${ejemplarLista}" var="ejemplar">
                                                <option value="${ejemplar.id}" ${ejemplar.id == param.id ? 'selected' : ''} >
                                                    ${ejemplar.nombre}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                    <label class="form-label col-6">
                                        Fecha de retorno
                                        <input type="date" class="form-control"  name="fechaRegreso" placeholder="yyyy-mm-dd" min="1997-01-01" max="2030-12-31">
                                    </label>
                                    <div class="col-3 mt-3">
                                        <button type="submit" class="btn btn-success w-100">Prestar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </c:catch>
</html>
