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
	<h2>
		<c:choose>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/users/add'}">
			Nowy Użytkownik
			</c:when>
			<c:otherwise>
			Edycja Użytkownika: <c:out value="${username}" />
			</c:otherwise>
		</c:choose>
	</h2>
	<sf:form method="POST" modelAttribute="userDTO">
		<c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/users/add'}">
			<sf:input path="username" placeholder="Login" cssErrorClass="error" /><br />
			<sf:errors path="username" cssClass="error" />
			<br />
		</c:if>
		<sf:password path="password" placeholder="Hasło" cssErrorClass="error" /><br />
		<sf:errors path="password" cssClass="error" />
		<br />
		<sf:password path="confirmPassword" placeholder="Potwierdź hasło" cssErrorClass="error" /><br />
		<sf:errors path="confirmPassword" cssClass="error" />
		<br />
		<sf:checkbox path="authorities" value="ROLE_ADMIN" />Administrator<br />
		<sf:checkbox path="authorities" value="ROLE_USER" />Użytkownik<br />
		<br />
		<c:if test="${requestScope['javax.servlet.forward.servlet_path'] != '/users/add'}">
			<sf:checkbox path="enabled" value="true" cssErrorClass="error" />Aktywny
			<sf:errors path="enabled" cssClass="error" />
			<br /><br />
		</c:if>
		<input type="submit" value="Zapisz" />
	</sf:form>
	</div>
</body>
</html>