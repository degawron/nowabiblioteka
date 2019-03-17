<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Wypożyczenie Książki</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<%@ include file="menu.jsp"%>

	<div class="dataform">
	<h2>Nowe Wypożyczenie</h2>
	<sf:form method="POST" modelAttribute="borrow">
		Książka <br />
		<sf:select path="book.id" id="bookOptions">
			<c:forEach items="${bookList}" var="book">
				<sf:option value="${book.id}">
					<c:out value="${book.title} - ${book.author}" />
				</sf:option>
			</c:forEach>
		</sf:select>
		<br />
		Czytelnik <br />
		<sf:select path="reader.id" id="readerOptions">
			<c:forEach items="${readerList}" var="reader">
				<sf:option value="${reader.id}">
					<c:out value="${reader.lastName} ${reader.firstName}" />
				</sf:option>
			</c:forEach>
		</sf:select>
		<br />
		Data wypożyczenia:<br />
		<sf:input type="date" path="dateOfBorrow" value="${date}" />		
		<br />
		Termin zwrotu:<br />
		<sf:radiobutton path="term" value="7" checked="checked"/>1 tydzień<br />
		<sf:radiobutton path="term" value="14" />2 tygodnie<br />
		<sf:radiobutton path="term" value="30" />1 miesiąc<br />
		<br />
		<input type="submit" value="Zapisz" />
	</sf:form>
	</div>
</body>
</html>