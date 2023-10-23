<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member_ex01.jsp 페이지</title>
	</head>
	<body>
		<h2>회원 가입 (화면 url 요청 : ContextPath/service/member_ex01.jsp 서비스 영역 연결)</h2>
		<!-- 
		1. join의 맵핑을 처리할 수 있는 메서드 생성
		2. command 패키지에 parameter 값을 처리할 수 있는 command 객체를 joinVO를 생성해서 값을 받아냄
		3. service 패키지를 생성하여 JoinService 인터페이스를 생성, JoinService에서 구현할 JoinServiceImpl 클래스 생성
		4. JoinserviceImpl을 어노테이션을 통해서 자동 객체 생성
		5. 컨트롤러 멤버 변수로 해당 객체를 자동 주입 시켜서 동작
		 -->
		 <form action="join" method="post">
		 	ID : <input type="text" name="id" size="10" ><br>
		 	PW : <input type="password" name="pw" size="10" ><br>
		 	NAME : <input type="text" name="name" size="10" ><br>
		 	<input type="submit" value="회원가입">
		 </form>
	</body>
</html>