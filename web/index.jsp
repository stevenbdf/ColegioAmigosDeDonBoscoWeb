<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio | Colegio Amigos de Don Bosco</title>
        <%@include file="/components/header.jsp" %>
        <c:if test="${categoriaLista == null || autorLista == null}">
            <c:redirect url="index"/>
        </c:if>
    </head>

    <jsp:useBean id="buscarForm" scope="request" class="javabeans.EjemplarBean" />
    <jsp:setProperty name="buscarForm" property="*" />
    <c:if test="${not empty param.buscar && param.buscar != vecesBuscado}">
        <jsp:forward page="/BuscarController" />
    </c:if>
    <body>
        <%@include file="/components/navbar.jsp" %>
        <div class="container-fluid ">
            <div class="row mt-3">
                <%@include file="/components/aside.jsp" %>
                <div class="col-10">
                    <div class="card">
                        <div class="card-body">
                            <h3>Consulta de ejemplares</h3>
                            <div class="my-3" style="height:1px;background-color: lightgray;"></div>
                            <p>Ingrese los datos de busqueda: </p>
                            ${resolved}
                            <form class="row" method="POST">
                                <input type="hidden" name="buscar" value="${vecesBuscado + 1}"></input>
                                <label class="form-label col-6">
                                    Titulo
                                    <input type="text" class="form-control"  name="nombre">
                                </label>
                                <label  class="form-label col-6">
                                    Descripción
                                    <input type="text" class="form-control" name="descripcion">
                                </label>
                                <label class="form-label col-6">
                                    Editorial
                                    <input type="text" class="form-control" name="editorial">
                                </label>
                                <label class="form-label col-6">
                                    ISBN
                                    <input type="text" class="form-control" name="isbn">
                                </label>
                                <label class="form-label col-6">
                                    Categoria
                                    <select class="form-select" name="idCategoria">
                                        <option value="0" selected>Selecciona una opcion</option>
                                        <c:forEach items="${categoriaLista}" var="categoria">
                                            <option value="${categoria.id}">
                                                ${categoria.descripcion}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                                <label class="form-label col-6">
                                    Autor
                                    <select class="form-select" name="idAutor">
                                        <option value="0" selected>Selecciona una opcion</option>
                                        <c:forEach items="${autorLista}" var="autor">
                                            <option value="${autor.id}">
                                                ${autor.nombres} ${autor.apellidos}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                                <div class="col-3 mt-3">
                                    <button type="submit" class="btn btn-success w-100">Realizar busqueda</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card mt-3">
                        <div class="card-body">
                            <h3>Resultado de la busqueda</h3>
                            <div class="my-3" style="height:1px;background-color: lightgray;"></div>

                            <div class="row">
                                <c:choose>
                                    <c:when test="${ejemplarLista.size() > 0}">
                                        <c:forEach items="${ejemplarLista}" var="ejemplar" varStatus="loop">
                                            <div class="col-4 my-2 d-flex align-items-stretch">
                                                <div class="card w-100">
                                                    <div class="card-body">
                                                        <h5>${loop.index + 1}. ${ejemplar.nombre}</h5>
                                                        <p class="m-0">${ejemplar.autor.nombres} ${ejemplar.autor.apellidos} - ${ejemplar.publicacion}</p>
                                                        <p class="m-0">${ejemplar.editorial} - ${ejemplar.categoria.descripcion}</p>
                                                        <p class="m-0">${ejemplar.descripcion}</p>
                                                        <p class="m-0">${ejemplar.isbn}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-4">
                                            Sin resultados en la busqueda
                                        </div>
                                    </c:otherwise>
                                </c:choose>      
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
