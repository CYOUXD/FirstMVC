<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login 입력 페이지</title>
	</head>
	<body>
		<h2>일반 테스트</h2>
		<form action="memLogin" method="post">
			ID : <input type="text" name="memId"><br>
			PW : <input type="password" name="memPw"><br>
			<input type="submit" value="로그인">
		</form>
		
		<h2>ModelAndView</h2>
		<form action="memLoginModelAndView" method="post">
			ID : <input type="text" name="memId"><br>
			PW : <input type="password" name="memPw"><br>
			<input type="submit" value="로그인">
		</form>
	</body>
</html>