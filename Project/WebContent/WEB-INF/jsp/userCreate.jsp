<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ新規登録</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/origin/common.css">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark mb-3">
		<a class="text-white">${userInfo.name}さん</a>
		<a href="LogoutServlet" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ新規登録</p>
		<p class="text-danger font-weight-blod text-center">${errorMessage}</p>

		<form method="post" action="UserCreateServlet">
			<div class="form-group row">
				<label for="loginId"
					class="col-sm-2 col-form-label font-weight-bold">ログインID</label>
				<div class="col-sm-10">
					<input type="text" name="loginId" id="loginId" value="${inputParameter.loginId}" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="password"
					class="col-sm-2 col-form-label font-weight-bold">パスワード</label>
				<div class="col-sm-10">
					<input type="password" name="password" id="password" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="passwordConfirm"
					class="col-sm-2 col-form-label font-weight-bold">パスワード(確認)</label>
				<div class="col-sm-10">
					<input type="password" name="passwordConfirm" id="passwordConfirm" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="userName"
					class="col-sm-2 col-form-label font-weight-bold">ユーザ名</label>
				<div class="col-sm-10">
					<input type="text" name="userName" id="userName" value="${inputParameter.userName}" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="birthdate"
					class="col-sm-2 col-form-label font-weight-bold">生年月日</label>
				<div class="col-sm-10">
					<input type="date" name="birthdate" id="birthdate"  value="${inputParameter.birthdate}" class="form-control">
				</div>
			</div>

		<div class="text-center">
			<button type="submit" class="btn btn-primary">登録</button>
			<a class="btn btn-outline-dark" href="UserListServlet" role="button">戻る</a>
		</div>

		</form>

	</div>
</body>
</html>