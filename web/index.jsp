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
                            <form class="row">
                                <label class="form-label col-6">
                                    Titulo
                                    <input type="text" class="form-control"  name="titulo">
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
                                    <select class="form-select" name="categoria">
                                        <option selected>Selecciona una opcion</option>
                                        <c:forEach items="${categoriaLista}" var="categoria">
                                            <option value="${categoria.id}">
                                                ${categoria.descripcion}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                                <label class="form-label col-6">
                                    Autor
                                    <select class="form-select" name="autor">
                                        <option selected>Selecciona una opcion</option>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
