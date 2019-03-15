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
	<h2>Edycja Użytkownika: <c:out value="${username}" /></h2>
	
	<c:out value="${username}" /><br />
	<c:out value="${password}" /><br />
	<c:out value="${confirPassword}" /><br />
	<c:out value="${newPassword}" /><br />
	<c:out value="${confirNewPassword}" /><br />
	<c:out value="${enabled}" /><br />
	<c:out value="${authorities}" /><br />
	
	<sf:form method="POST" modelAttribute="userDTO">
		<sf:errors path="*" element="div" cssClass="error" />
		
		<sf:label path="newPassword">Hasło:</sf:label><br />
		<sf:password path="newPassword" cssErrorClass="error" />
		<sf:errors path="newPassword" cssClass="error" />
		<br />
		<sf:label path="confirmNewPassword">Potwierdź hasło:</sf:label><br />
		<sf:password path="confirmNewPassword" cssErrorClass="error" />
		<sf:errors path="confirmNewPassword" cssClass="error" />
		<br />
		<sf:label path="authorities">Uprawnienia:</sf:label><br />
		<sf:checkbox path="authorities" value="ROLE_ADMIN" />Administrator<br />
		<sf:checkbox path="authorities" value="ROLE_USER" />Użytkownik<br />
		<sf:errors path="authorities" cssClass="error" />
		<br />
		<sf:label path="enabled">Aktywny:</sf:label><br />
		<sf:checkbox path="enabled" value="1"/>
		<sf:errors path="enabled" cssClass="error" />
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
	</div>
</body>
</html>