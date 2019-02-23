<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
	<head>
		<title>Nowa Biblioteka</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
	</head>
	<body>
		<h1>Witamy w Nowej Bibliotece</h1>
		<a href="<c:url value="/readers" />">Czytelnicy</a> |
		<a href="<c:url value="/books" />">Książki</a> |
		<a href="<c:url value="/borrows" />">Wypożyczenia</a>
	</body>
</html>