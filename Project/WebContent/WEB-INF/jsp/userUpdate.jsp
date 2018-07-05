<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/origin/common.css">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark mb-3">
		<a class="text-white">${LoginUserInfo.name }さん</a>
		<a href="LogoutServlet" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ情報更新</p>
		<p class="text-danger font-weight-blod text-center">${errorMessage}</p>

		<form method="post" action="UserUpdateServlet">
			<div class="form-group row">
				<label for="loginId"
					class="col-sm-2 col-form-label font-weight-bold">ログインID</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">${userInfoRecord.loginId }</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="password"
					class="col-sm-2 col-form-label font-weight-bold">パスワード</label>
				<div class="col-sm-10">
					<input type="password" name="password" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="passwordConfirm"
					class="col-sm-2 col-form-label font-weight-bold">パスワード(確認)</label>
				<div class="col-sm-10">
					<input type="password" name="passwordConfirm" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="name"
					class="col-sm-2 col-form-label font-weight-bold">ユーザ名</label>
				<div class="col-sm-10">
					<input type="text" name="name" value="${userInfoRecord.name }" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="birthdate"
					class="col-sm-2 col-form-label font-weight-bold">生年月日</label>
				<div class="col-sm-10">
					<input type="date" name="birthDate" value="${userInfoRecord.birthDate }"
						class="form-control">
				</div>
			</div>


		<div class="text-center">
				<input type="hidden" name="loginId" value="${userInfoRecord.loginId }">
				<button type="submit" class="btn btn-primary">更新</button>
			<a class="btn btn-secondary" href="UserListServlet" role="button">戻る</a>
		</div>
		</form>

	</div>
</body>
</html>