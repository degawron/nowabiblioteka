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
	
	<c:forEach items="${borrowingList}" var="borrowing">
		<tr>
		<td><c:out value="${borrowing.id}"/></td>
		<td><c:out value="${borrowing.book.title}"/></td>
		<td><c:out value="${borrowing.reader.firstName}"/> <c:out value="${borrowing.reader.lastName}"/></td>
		<td><fmt:formatDate value="${borrowing.dateOfBorrow}" pattern="dd-MM-yyyy" /></td>
		<td><c:out value="${borrowing.term}"/></td>
		<td>
			<c:if test="${not empty borrowing.dateOfReturn}">
				<fmt:formatDate value="${borrowing.dateOfReturn}" pattern="dd-MM-yyyy" />)
			</c:if>
		</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>