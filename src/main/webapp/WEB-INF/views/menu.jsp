<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
	<script>
		function openNav() {
			document.getElementById("sidenav").style.width = "250px";
		}

		function closeNav() {
			document.getElementById("sidenav").style.width = "0";
		}
	</script>
	<div id="sidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a href="<c:url value="/start" />">Start</a>
		<br/>
		<a href="<c:url value="/readers" />">Czytelnicy</a>
		<a href="<c:url value="/books" />">Książki</a>
		<a href="<c:url value="/borrows" />">Wypożyczenia</a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="<c:url value="/users" />">Użytkownicy</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<br/>
		<a href="<c:url value="/logout" />">Wyloguj&nbsp;się</a>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
		<br/>
		<a href="<c:url value="/login" />">Zaloguj&nbsp;się</a>
		</sec:authorize>
	</div>
	<h1><span style="font-size: 30px; cursor: pointer" onclick="openNav()">
	&#9776; Biblioteka 
	<c:choose>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/readers'}">
			 &#8658; Czytelnicy
			</c:when>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/books'}">
			 &#8658; Książki
			</c:when>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/borrows'}">
			 &#8658; Wypożyczenia
			</c:when>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/users'}">
			 &#8658; Użytkownicy
			</c:when>
		</c:choose>
	</span></h1>