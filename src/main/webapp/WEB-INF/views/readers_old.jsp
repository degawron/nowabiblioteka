<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Czytelnicy</title>
<link rel="stylesheet" type="text/css" href="/resources/style.css">
</head>
<body>
	<h1>Lista Czytelników</h1>
	<c:forEach items="${readerList}" var="reader">
		<li id="czytelnik_<c:out value="${reader.id}"/>">
			<div class="czytelnicy.nazwa" style="display: inline">
				<c:out value="${reader.firstName}" />
				<c:out value="${reader.lastName}" />
				<c:if test="${reader.active == 0}">
					(nieaktywny)
				</c:if>
				<c:if test="${reader.active == 1}">
					(aktywny)
				</c:if>
			</div>
		</li>
	</c:forEach>
</body>
</html>