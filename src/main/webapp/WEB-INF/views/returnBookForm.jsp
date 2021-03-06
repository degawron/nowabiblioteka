<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Zwrot Książki</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<%@ include file="menu.jsp"%>

	<div class="dataform">
	<h2>Zwrot Ksiązki</h2>
	<sf:form method="POST" modelAttribute="borrow">
		<sf:hidden path="id"/>
		<sf:hidden path="book.id"/>
		Data zwrotu:<br />
		<sf:input type="date" path="dateOfReturn" value="${date}" />		
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
	</div>
</body>
</html>