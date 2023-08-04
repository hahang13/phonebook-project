<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
	<title>회원정보란</title>
	<link rel="stylesheet" href="../css/phonemember.css">

</head>
<body>
    <header>
      <nav class="navigation">
        <a class = "log" href="../phonebooks/removesession">로그아웃</a>
      </nav>
    </header>
    <div class="wrapper">
      <!-- <div class="from-box login"> -->
        <h2 >전화부</h2>
        <table class="info">
        <thead class="thead">
        	<tr>
				<th> 이메일 </th>
				<th> 이름 </th>
				<th> 전화번호 </th>
				<th> 주소 </th>
				<th> 그룹 </th>
			</tr>
        </thead>
		<tbody>
			<c:forEach var="phonebooks" items="${memlist}">
				<tr>
					<td>${phonebooks.email}</td>
					<td>${phonebooks.nmname}</td>
					<td>${phonebooks.phoneNumber}</td>
					<td>${phonebooks.address}</td>
					<td>${phonebooks.gubun}</td>
					<td>
						<form action="/phonebooks/delmem/${phonebooks.phoneNumber}" method="post">
							<input type="hidden" name="phoneNumber" value="${phonebooks.phoneNumber}">
							<button type="submit" class="delmem">삭제</button>
      					</form>
      				</td>
					<td>
						<button class="badge bg-secondary" data-bs-toggle="modal" data-bs-target="#editModal${phonebooks.phoneNumber}">
						  수정
						</button>
						
						<!-- Modal -->
						<div class="modal fade" id="editModal${phonebooks.phoneNumber}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">주소록 수정</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <form action="/phonebooks/modify?phoneNumber=${phonebooks.phoneNumber}" method="post">
						    		<label class="form-label">이메일:</label>
									<input type="text" placeholder="예: honggildong@gmail.com" name="email" class="form-control" required="required">
									<label class="form-label">이름:</label>
									<input type="text" placeholder="예:홍길동" name="nmname" class="form-control" required="required">
									<label class="form-label">전화번호:</label>
									<input type="text" disabled="disabled" placeholder="${phonebooks.phoneNumber}" name="phoneNumber" class="form-control">
						    		<label class="form-label">주소:</label>
									<input type="text" placeholder="예: 서울시 강남구" name="address" class="form-control" required="required">
									<label class="form-label">그룹명:</label><br/>
									<div>
										<input type="radio" name="gubunnum" value="10" checked="checked">가족
										<input type="radio" name="gubunnum" value="20">친구
										<input type="radio" name="gubunnum" value="30">기타
									</div>
									<div class="modal-footer">
							        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
							        	<button type="submit" class="btn btn-dark">저장</button>
							        </div>
								</form>
						      </div>
						    </div>
						  </div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
		      	<form method="post" action="/phonebooks/addmem">
					<td><input type="text" name="email"></td>
		      		<td><input type="text" name="nmname"></td>
		      		<td><input type="text" name="phoneNumber"></td>
		      		<td><input type="text" name="address"></td>
		      		<td><input type="text" name="gubun"></td>
		      		<td collapse="2"><button type="submit">회원 추가</button></td>
		      	</form>
			</tr>
		</tfoot>      	
    </table>
</div>
</body>
</html>


