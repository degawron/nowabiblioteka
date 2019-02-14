<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page session="false"%>
<html>
	<head>
		<title>Nowa Biblioteka</title>
		<link rel="stylesheet" type="text/css"
		href="<c:url value="/resources/style.css" />">
	</head>
	<body>
		<h1>Witamy w Nowej Bibiotece</h1>
		<a href="<c:url value="/spittles" />">Spittle</a> |
		<a href="<c:url value="/spitter/register" />">Rejestracja</a>
	</body>
</html>