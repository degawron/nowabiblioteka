<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Książki</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
	<h1>Lista Książek</h1>
	<table>
		<tr>
			<th style="width: 10%">Lp.</th>
			<th style="width: 30%">Tytuł</th>
			<th style="width: 25%">Autor</th>
			<th style="width: 10%">Rok</th>
			<th style="width: 10%">Ilość</th>
			<th style="width: 15%">Akcja</th>
		</tr>
		<c:forEach items="${bookList}" var="book">
			<tr>
				<td><c:out value="${book.id}" /></td>
				<td><c:out value="${book.title}" /></td>
				<td><c:out value="${book.author}" /></td>
				<td><c:out value="${book.year}" /></td>
				<td><c:out value="${book.quantity}" /></td>
				<td>todo</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>