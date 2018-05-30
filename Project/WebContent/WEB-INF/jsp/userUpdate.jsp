<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<a class="text-white">ユーザ名さん</a>
		<a href="#" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ情報更新</p>

		<form>
			<div class="form-group row">
				<label for="loginId"
					class="col-sm-2 col-form-label font-weight-bold">ログインID</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">id0001</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="password"
					class="col-sm-2 col-form-label font-weight-bold">パスワード</label>
				<div class="col-sm-10">
					<input type="password" id="password" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="passwordConfirm"
					class="col-sm-2 col-form-label font-weight-bold">パスワード(確認)</label>
				<div class="col-sm-10">
					<input type="password" id="passwordConfirm" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="userName"
					class="col-sm-2 col-form-label font-weight-bold">ユーザ名</label>
				<div class="col-sm-10">
					<input type="text" id="userName" value="田中太郎" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="birthdate"
					class="col-sm-2 col-form-label font-weight-bold">生年月日</label>
				<div class="col-sm-10">
					<input type="text" id="birthdate" value="1989/04/26"
						class="form-control">
				</div>
			</div>
		</form>

		<div class="text-center">
			<button type="button" class="btn btn-primary">更新</button>
			<button type="button" class="btn btn-default">戻る</button>
		</div>

	</div>
</body>
</html>