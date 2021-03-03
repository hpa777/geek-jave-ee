<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="User list"/>
</jsp:include>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">EShop</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">List</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/user/new" var="userNewUrl"/>
            <a class="btn btn-primary" href="${userNewUrl}">Add User</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <th scope="row"><c:out value="${user.id}"/></th>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td>
                        <c:url value="/user/edit" var="userEditUrl">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${userEditUrl}"><i class="fas fa-edit"></i></a>
                        <c:url value="/user/delete" var="userDeleteUrl">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a class="btn btn-danger" href="${userDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>
</body>
</html>