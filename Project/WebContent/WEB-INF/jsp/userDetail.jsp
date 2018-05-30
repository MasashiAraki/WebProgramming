<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報詳細参照</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/origin/common.css">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark mb-3">
		<a class="text-white">ユーザ名さん</a>
		<a href="#" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ情報詳細参照</p>

		<form class="text-center">
			<div class="form-group row">
				<label for="loginId"
					class="col-sm-2 col-form-label font-weight-bold">ログインID</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">id0001</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="userName"
					class="col-sm-2 col-form-label font-weight-bold">ユーザ名</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">田中太郎</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="birthdate"
					class="col-sm-2 col-form-label font-weight-bold">生年月日</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">1989年04月26日</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="createDate"
					class="col-sm-2 col-form-label font-weight-bold">登録日時</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">2017年01月01日 10:50</p>
				</div>
			</div>

			<div class="form-group row">
				<label for="updateDate"
					class="col-sm-2 col-form-label font-weight-bold">更新日時</label>
				<div class="col-sm-10">
					<p class="form-control-plaintext">2017年02月01日 01:05</p>
				</div>
			</div>
		</form>

		<div class="text-center">
			<button type="button" class="btn btn-default">戻る</button>
		</div>

	</div>
</body>
</html>