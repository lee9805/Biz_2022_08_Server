
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
nav ul {
	list-style: none;
	display: flex;
	background-color: #96dbff;
	color: white;
}

nav li {
	padding: 12px 16px;
	margin: 5px;
	cursor: pointer;
}

nav li:hover {
	box-shadow: 2px 2px 2px black;
}

nav li:nth-of-type(2) {
	margin-left: auto;
}

nav a {
	text-decoration: none;
	color: inherit;
}

form#logout-form {
	display: none;
}
</style>
</head>
<body>
	<header class="w3-container w3-blue w3-text-white">
		<h1>하하호호</h1>
	</header>
	<nav>
		<ul>
			<sec:authorize access="isAnonymous()">
				<li><a href="${rootPath}/">Home</a></li>
				<li><a href="${rootPath}/user/login">로그인</a></li>
				<li><a href="${rootPath}/user/join">회원가입</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="logout">로그아웃</li>
				<sec:authorize access="hasRole('ROLE_USER')">
					<li><a href="${rootPath}/user/mypage">Mypage</a></li>
				</sec:authorize>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${rootPath}/admin">관리자</a></li>
			</sec:authorize>
		</ul>
	</nav>
	<section class="w3-container w3-padding-16">
		<c:choose>
			<c:when test="${LAYOUT == 'LOGIN'}">
				<%@ include file="/WEB-INF/views/user/login.jsp"%>
			</c:when>
			<c:when test="${LAYOUT == 'JOIN'}">
				<%@ include file="/WEB-INF/views/user/join.jsp"%>
			</c:when>
			<c:otherwise>
				<table class="memo">
					<tr>
						<th>SEQ</th>
						<th>작성일자</th>
						<th>작성시각</th>
						<th>메모</th>
					</tr>
					<c:if test="${empty TODOS}">
						<tr>
							<td colspan="4">메모가 없습니다</td>
						</tr>
					</c:if>
					
					
					
					
					
					
					<c:forEach items="${TODOS}" var="TODO" varStatus="INDEX">
						<tr data-seq="${TODO.t_seq}">
							<td>${INDEX.count}</td>
							<td>${TODO.t_sdate}</td>
							<td>${TODO.t_stime}</td>
							<td>${TODO.t_content}</td>
							<td>${TODO.t_author}</td>
							<td><a href="${rootPath}/todo/insert?seq=${TODO.t_seq}">수정</a></td>
							<td><input type="checkbox" value="${TODO.t_complete}"/>완료</td>
							<td><a href="${rootPath}/delete">Todo 삭제하기</a></td>
						</tr>
					</c:forEach>
				</table>
				<a href="${rootPath}/todo/insert">Todo 추가하기</a>
			</c:otherwise>
		</c:choose>
	</section>
	<form:form id="logout-form" action="${rootPath}/logout" method="POST" />
	<script>
		document.querySelector("li.logout")?.addEventListener("click",()=>{
			document.querySelector("form#logout-form")?.submit()
		})
	</script>
</body>
</html>