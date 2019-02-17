<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Czytelnicy</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Nowy Czytelnik</h1>
	<form method="POST">
		Imię:<br/>
		<input type="text" name="firstName" /><br/>
		Nazwisko:<br/>
		<input type="text" name="lastName" /><br/>
		<input type="submit" value="Dodaj" />
	</form>
</body>
</html>