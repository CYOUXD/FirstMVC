<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>memLogin_ok.jsp</title>
	</head>
	<body>
		<h2>로그인 시 입력한 결과 콘솔에서 확인</h2>
		
		<h2>Model로 넘어온 값 처리</h2>
		memId : ${memId }<br>
		memPw : ${memPw }<br>
		
		mem 객체 값 확인 : ${mem.memId }와 ${mem.memPw }, ${mem.name }<br>
		${mem }<br>
	</body>
</html>