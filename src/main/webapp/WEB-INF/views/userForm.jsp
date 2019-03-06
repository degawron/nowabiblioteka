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
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/users/add'}">
			Nowy Użytkownik
			</c:when>
			<c:otherwise>
			Edycja Użytkownika
			</c:otherwise>
		</c:choose>
	</h2>
	<sf:form method="POST" modelAttribute="usersView">
		<sf:label path="username">Login:</sf:label><br />
		<sf:input path="username" cssErrorClass="error" />
		<sf:errors path="username" cssClass="error" />
		<br />
		<sf:label path="password">Hasło:</sf:label><br />
		<sf:password path="password" cssErrorClass="error" />
		<sf:errors path="password" cssClass="error" />
		<br />
		<sf:hidden path="enabled" />
		<sf:label path="authorities">Uprawnienia:</sf:label><br />
		<sf:checkbox path="authorities" value="ROLE_ADMIN"/>Admin
		<sf:checkbox path="authorities" value="ROLE_USER"/>User
		<sf:errors path="authorities" cssClass="error" />
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
</body>
</html>