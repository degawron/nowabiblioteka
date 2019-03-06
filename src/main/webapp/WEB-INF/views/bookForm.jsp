<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Czytelnicy</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<%@ include file="menu.jsp"%>

	<h2>
		<c:choose>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/books/add'}">
			Nowa Książka
			</c:when>
			<c:otherwise>
			Edycja Książki
			</c:otherwise>
		</c:choose>
	</h2>
	<sf:form method="POST" modelAttribute="book">
		<sf:hidden path="id" />
		<sf:label path="title">Tytuł:</sf:label><br />
		<sf:input path="title" />
		<sf:errors path="title" cssClass="error" />
		<br />
		<sf:label path="author">Autor:</sf:label><br />
		<sf:input path="author" />
		<sf:errors path="author" cssClass="error" />
		<br />
		<sf:label path="year">Rok:</sf:label><br />
		<sf:input path="year" type="number" />
		<sf:errors path="year" cssClass="error" />
		<br />
		<sf:label path="quantity">Ilość:</sf:label><br />
		<sf:input path="quantity" type="number" />
		<sf:errors path="quantity" cssClass="error" />
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
</body>
</html>