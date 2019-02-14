<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Wypożyczenia</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
	<h1>Lista Wypożyczeń</h1>
	<table>
			<tr>
				<th style="width: 5%">Lp.</th>
				<th style="width: 25%">Ksiażka</th>
				<th style="width: 25%">Czytelnik</th>
				<th style="width: 15%">Data wypożyczenia</th>
				<th style="width: 15%">Długość wypożyczenia<br>(aktualnie / max)</th>
				<th style="width: 15%">Data zwrotu</th>
			</tr>
	
	<c:forEach items="${borrowingsList}" var="borrowings">
		<tr>
		<td><c:out value="${borrowings.id}"/></td>
		<td><c:out value="${borrowings.book.title}"/></td>
		<td><c:out value="${borrowings.reader.firstName}"/> <c:out value="${borrowings.reader.lastName}"/></td>
		<td><fmt:formatDate value="${borrowings.dateOfBorrow}" pattern="dd-MM-yyyy" /></td>
		<td><c:out value="${borrowings.term}"/></td>
		<td>
			<c:if test="${not empty borrowings.dateOfReturn}">
				<fmt:formatDate value="${borrowings.dateOfReturn}" pattern="dd-MM-yyyy" />)
			</c:if>
		</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>