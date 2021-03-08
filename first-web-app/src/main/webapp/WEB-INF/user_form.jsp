<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="User form"/>
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
            <c:url value="/user/" var="userSubmitUrl"></c:url>
            <form action="${userSubmitUrl}" method="post">
                <input type="hidden" id="id" name="id" value="${user.id}">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" value="${user.name}">
                </div>
                <div class="form-group">
                    <label>E-mail</label>
                    <input type="text" class="form-control" id="email" name="email" placeholder="Enter email" value="${user.email}">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="number" class="form-control" id="phone" name="phone" placeholder="Enter phone" value="${user.phone}">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>
</body>
</html>