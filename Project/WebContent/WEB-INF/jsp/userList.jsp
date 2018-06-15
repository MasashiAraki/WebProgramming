<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<a class="text-white">${userInfo.name}さん</a>
		<a href="LogoutServlet" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ一覧</p>

		<div class="text-right">
			<p><button type="button" class="btn btn-outline-primary">新規登録</button></p>
		</div>

		<form>
			<div class="form-group row">
				<label for="loginId"
					class="col-sm-2 col-form-label font-weight-bold">ログインID</label>
				<div class="col-sm-10">
					<input type="text" id="loginId" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="userName"
					class="col-sm-2 col-form-label font-weight-bold">ユーザ名</label>
				<div class="col-sm-10">
					<input type="text" id="userName" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="birthdate"
					class="col-sm-2 col-form-label font-weight-bold">生年月日</label>
				<div class="row col-sm-10">
					<div class="col-sm-5">
						<input type="date" id="birthdate" class="form-control">
					</div>

					<div class="col-sm-2 text-center form-control-plaintext">
						～
					</div>

					<div class="col-sm-5">
						<input type="date" id="birthdate" class="form-control">
					</div>

				</div>
			</div>
		</form>

		<div class="text-right">
			<button type="button" class="btn btn-primary btn-lg">検索</button>
		</div>

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
				<tr>
					<th scope="row" class="align-middle">id0001</th>
					<td class="align-middle">田中太郎</td>
					<td class="align-middle">1989年04月26日</td>
					<td>
						<button type="button" class="btn btn-primary">検索</button>
						<button type="button" class="btn btn-success">更新</button>
						<button type="button" class="btn btn-danger">削除</button>
					</td>
				</tr>
				<tr>
					<th scope="row" class="align-middle">id0002</th>
					<td class="align-middle">佐藤二郎</td>
					<td class="align-middle">2001年11月12日</td>
					<td>
						<button type="button" class="btn btn-primary">検索</button>
						<button type="button" class="btn btn-success">更新</button>
						<button type="button" class="btn btn-danger">削除</button>
					</td>
				</tr>
				<tr>
					<th scope="row" class="align-middle">id0003</th>
					<td class="align-middle">佐川真司</td>
					<td class="align-middle">2000年01月01日</td>
					<td>
						<button type="button" class="btn btn-primary">検索</button>
						<button type="button" class="btn btn-success">更新</button>
						<button type="button" class="btn btn-danger">削除</button>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
</body>
</html>