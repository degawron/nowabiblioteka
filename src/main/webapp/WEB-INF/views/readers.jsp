<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Czytelnicy</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
	<%@ include file="menu.jsp"%>
	<a class="add" href="<c:url value="/readers/add" />">Dodaj czytelnika</a>
	<table>
		<tr>
			<th style="width: 10%">ID</th>
			<th style="width: 25%">Imię</th>
			<th style="width: 30%">Nazwisko</th>
			<th style="width: 10%">Ilość wypożyczeń</th>
			<th style="width: 10%">Niezwrócone</th>
			<th style="width: 15%">Akcja</th>
		</tr>

		<c:forEach items="${readersViewList}" var="reader">
			<tr>
				<td><c:out value="${reader.id}" /></td>
				<td><c:out value="${reader.firstName}" /></td>
				<td><c:out value="${reader.lastName}" /></td>
				<td><c:out value="${reader.borrows}" /></td>
				<td><c:out value="${reader.notReturned}" /></td>
				<td><a href="<c:url value="/readers/edit/${reader.id}" />">Edytuj</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>