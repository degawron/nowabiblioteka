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
	<h1>
		<c:choose>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/books/add'}">
			Nowa Książka
			</c:when>
			<c:otherwise>
			Edycja Książki
			</c:otherwise>
		</c:choose>
	</h1>
	<sf:form method="POST" modelAttribute="book">
		<sf:hidden path="id" />
		Tytuł:<br />
		<sf:input path="title" />
		<br />
		Autor:<br />
		<sf:input path="author" />
		<br />
		Rok:<br />
		<sf:input path="year" />
		<br />
		Ilość:<br />
		<sf:input path="quantity" />
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
</body>
</html>