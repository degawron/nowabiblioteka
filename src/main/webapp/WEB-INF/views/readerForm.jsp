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
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/readers/add'}">
			Nowy Czytelnik
			</c:when>
			<c:otherwise>
			Edycja Czytelnika
			</c:otherwise>
		</c:choose>
	</h2>
	<sf:form method="POST" modelAttribute="reader">
		<sf:hidden path="id" />
		<sf:label path="firstName">Imię</sf:label><br />
		<sf:input path="firstName" cssErrorClass="error" />
		<sf:errors path="firstName" cssClass="error" />
		<br />
		<sf:label path="lastName">Nazwisko:</sf:label><br />
		<sf:input path="lastName" cssErrorClass="error" />
		<sf:errors path="lastName" cssClass="error" />
		<br />
		<sf:hidden path="enabled" />
		<input type="submit" value="Zapisz" />
	</sf:form>
</body>
</html>