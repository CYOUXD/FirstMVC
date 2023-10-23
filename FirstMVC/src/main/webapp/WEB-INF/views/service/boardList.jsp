<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>boardList 게시물 목록</title>
	</head>
	<body>
		<h2>게시글 목록</h2>
		<table>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>타이틀</th>
				<th>제목</th>
				<th>비고</th>
			</tr>
			<c:forEach var="list" items="${boardList }" varStatus="num">
				<tr>
					<td>${num.index +1 }</td>
					<td>${list.name }</td>
					<td>${list.title }</td>
					<td>${list.content }</td>
					<!-- 
					1. boardDelete로 삭제 처리
					2. 삭제 후에는 목록 화면으로 리다이렉트
					 -->
					
					<td><input type="button" value="삭제" onclick="location.href='boardDelete?num=${num.index}'"></td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- 게시글 작성 페이지로 이동 -->
		<a href="boardRegister">게시글 작성</a>
	</body>
</html>