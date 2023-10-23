<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<form action="login" method="post">
			ID : <input type="text" name="id"><br>
			PW : <input type="password" name="pw"><br>
			<input type="submit" value="확인"><br>
			<input type="button" value="회원가입" onclick="location.href='member_ex01'">
		</form>
		
		${msg }<br>
	</body>
</html>