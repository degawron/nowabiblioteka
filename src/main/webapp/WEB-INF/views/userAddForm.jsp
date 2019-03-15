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

	<div class="dataform">
	<h2>Nowy Użytkownik</h2>
	<sf:form method="POST" modelAttribute="userDTO">
		<sf:label path="username">Login:</sf:label><br />
		<sf:input path="username" cssErrorClass="error" />
		<sf:errors path="username" cssClass="error" />
		<br />
		<sf:label path="password">Hasło:</sf:label><br />
		<sf:password path="password" cssErrorClass="error" />
		<sf:errors path="password" cssClass="error" />
		<br />
		<sf:label path="confirmPassword">Potwierdź hasło:</sf:label><br />
		<sf:password path="confirmPassword" cssErrorClass="error" />
		<sf:errors path="confirmPassword" cssClass="error" />
		<br />
		<sf:checkbox path="authorities" value="ROLE_ADMIN" />Administrator<br />
		<sf:checkbox path="authorities" value="ROLE_USER" />Użytkownik<br />
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
	</div>
</body>
</html>