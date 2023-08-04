<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
  <meta name=viewport content="width=device-width,initial-scale=1.0">
  <title>
    메인페이지
  </title> 
  <link rel="stylesheet" href="../css/style.css">
  <style type="text/css">
  .id_ok{
  color: #008000;
  display: none;
  }
  .id_already {
  color: #ff0000;
  display: none;
  }
  </style>
</head>
<body>
    <header>
      <h2 class="logo">hello World</h2>
      <nav class="navigation">
        <a href="#">Home</a>
        <a href="#">About</a>
        <a href="#">Services</a>
        <button class="btnlogin-popup">Login</button>
      </nav>
    </header>
    <div class="wrapper">
      <span class="icon-close"><ion-icon name="close-outline"></ion-icon></span>
      <div class="from-box login">
      <!-- 로그인  -->      
        <h2>로그인</h2>
        <form method="post" action="/phonebooks/login">
          <div class="input-box">
            <span class="icon"><ion-icon name="mail"></ion-icon></span>
            <input type="email" name="email" required>
            <label>Email</label>
          </div>  
          <div class="input-box">
            <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
            <input type="password" name="password" required>
            <label>Password</label>
          </div>
          <div class="remember-forgot">
            <label><input type="checkbox">로그인 저장</label>
            <a href="#">비밀번호를 잃어버린 경우</a>
          </div>
          <button type="submit" class="btn" id="registerbtn">Login</button>
          <div class="login-register">
            <p>계정이 없으신가요?<a href="#" class="register-link">가입하기</a></p>
          </div>
        </form>
      </div>
      <!-- 가입  -->      
      <div class="from-box register">
        <h2>가입하기</h2>
        <form method="post" action="/phonebooks/addjoin">
          <div class="input-box">
            <span class="icon"><ion-icon name="person"></ion-icon></span>
            <input type="text" name="nmname" required>
            <label>이름</label>
          </div>  
          <div class="input-box">
            <span class="icon"><ion-icon name="call"></ion-icon></span>
            <input type="text" name="phoneNumber" required>
            <label>핸드폰번호</label>
          </div> 
          <!-- 아이디 중복 확인 -->
	          <div class="input-box">
	            <span class="icon"><ion-icon name="call"></ion-icon></span>
	            <input type="text" name="email" id="email" oninput="checkId(email.value)"  >
	            <label>이메일</label>
	          </div>
		          <span class="id_ok">사용가능</span> 
		          <span class="id_already">사용불가</span> 
           <!-- 비밀번호 확인체크  -->

              <div class="input-box">
                <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                <input type="password" id="password1" required>
                <label>비밀번호</label>
              </div>
              <div class="input-box">
                <input type="password" name="pw" id="password2" required>
                <label>비밀번호확인</label>
                  <span class="icon">
                    <input type="button" onclick="test()" value="확인">
                  </span>
              </div>

           <!-- ★ -->
          <div class="remember-forgot">
            <label><input type="checkbox">이용약관에 동의합니다</label>
          </div>
          <button type="submit" class="btn">가입하기</button>
          <div class="login-register">
            <p>계정이 있는 회원인 경우<a href="#" class="login-link">로그인</a></p>
          </div>
        </form>
      </div>
    </div>
    </div>
    <script src="/js/script.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>