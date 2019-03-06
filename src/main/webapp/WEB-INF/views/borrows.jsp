<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Wypożyczenia</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<%@ include file="menu.jsp"%>
	<a class="add" href="<c:url value="/borrows/new" />">Nowe wypożyczenie</a>
	<table>
		<tr>
			<th style="width: 5%">ID</th>
			<th style="width: 25%">Ksiażka</th>
			<th style="width: 25%">Czytelnik</th>
			<th style="width: 15%">Data wypożyczenia</th>
			<th style="width: 15%">Długość wypożyczenia<br>(aktualnie /
				max)
			</th>
			<th style="width: 15%">Data zwrotu</th>
		</tr>

		<c:forEach items="${borrowsViewList}" var="borrow">
			<tr>
				<td><c:out value="${borrow.id}" /></td>
				<td><c:out value="${borrow.book.title}" /></td>
				<td><c:out value="${borrow.reader.firstName}" /> <c:out
						value="${borrow.reader.lastName}" /></td>
				<td><fmt:formatDate value="${borrow.dateOfBorrow}"
						pattern="dd-MM-yyyy" /></td>
				<c:choose>
						<c:when test="${borrow.borrowTime>borrow.term}">
							<td style="background-color: red"><c:out value="${borrow.borrowTime}/${borrow.term}" /></td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${borrow.borrowTime}/${borrow.term}" /></td>
						</c:otherwise>
				</c:choose>
				<td><c:choose>
						<c:when test="${not empty borrow.dateOfReturn}">
							<fmt:formatDate value="${borrow.dateOfReturn}"
								pattern="dd-MM-yyyy" />
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/borrows/return/${borrow.id}" />">Zwróc</a>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>