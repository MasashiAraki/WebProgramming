<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ一覧</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/origin/common.css">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark mb-3">
		<a class="text-white">${LoginUserInfo.name}さん</a>
		<a href="LogoutServlet" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ一覧</p>

		<div class="text-right">
			<form method="get" action="UserCreateServlet">
				<p><button type="submit" class="btn btn-outline-primary">新規登録</button></p>
			</form>
		</div>

		<form method="post" action="UserListServlet">
			<div class="form-group row">
				<label for="loginId"
					class="col-sm-2 col-form-label font-weight-bold">ログインID</label>
				<div class="col-sm-10">
					<input type="text" name="inputLoginId" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label font-weight-bold">ユーザ名</label>
				<div class="col-sm-10">
					<input type="text" name="inputName" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="birthdate"
					class="col-sm-2 col-form-label font-weight-bold">生年月日</label>
				<div class="row col-sm-10">
					<div class="col-sm-5">
						<input type="date" name="inputMinBirthdate" class="form-control">
					</div>

					<div class="col-sm-2 text-center form-control-plaintext">～</div>

					<div class="col-sm-5">
						<input type="date" name="inputMaxBirthdate" class="form-control">
					</div>

				</div>
			</div>
			<div class="text-right">
				<button type="submit" class="btn btn-primary btn-lg">検索</button>
			</div>
		</form>

		<hr>

		<table class="table table-striped ">
			<thead class="thead-dark">
				<tr>
					<th scope="col">ログインID</th>
					<th scope="col">ユーザ名</th>
					<th scope="col">生年月日</th>
					<th scope="col"></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.loginId}</td>
						<td>${user.name}</td>
						<td>${user.birthDate}</td>

						<td>
							<a class="btn btn-primary" href="UserDetailServlet?id=${user.id}" role="button">詳細</a>

							<c:if test="${LoginUserInfo.loginId == 'admin' || LoginUserInfo.loginId == user.loginId}">
								<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}"role="button">更新</a></c:if>

							<c:if test="${LoginUserInfo.loginId == 'admin' }">
								<a class="btn btn-danger"	href="UserDeleteServlet?id=${user.id}" role="button">削除</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>