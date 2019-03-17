<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa Biblioteka - Brak dostępu</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
</head>
<body>
	<div style="text-align: center;">
		<h2>Przykro nam, ale nie masz dostępu do tej strony</h2>
		<a href="<c:url value="/start" />">Powrót do strony głównej</a>
	</div>
</body>
</html>