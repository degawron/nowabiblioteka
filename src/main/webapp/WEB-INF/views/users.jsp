<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Użytkownicy</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
	<%@ include file="menu.jsp"%>
	<a class="add" href="<c:url value="/users/add" />">Dodaj użytkownika</a>
	<table>
		<tr>
			<th style="width: 30%">Login</th>
			<th style="width: 30%">Hasło</th>
			<th style="width: 10%">Aktywny</th>
			<th style="width: 10%">Uprawnienia</th>
			<th style="width: 20%">Akcja</th>
		</tr>

		<c:forEach items="${usersViewList}" var="user">
			<tr>
				<td><c:out value="${user.username}" /></td>
				<td><c:out value="${user.password}" /></td>
				<td><c:out value="${user.enabled}" /></td>
				<td><c:out value="${user.authorities}" /></td>
				<td><a href="<c:url value="/users/edit/${user.username}" />">Edytuj</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>