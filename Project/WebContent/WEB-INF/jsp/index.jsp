<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/origin/common.css">
</head>
<body>
	<div class="container">
		<p class="h1 mb-5 mt-3 font-weight-bold text-center">ログイン画面</p>
		<p class="text-danger font-weight-blod text-center">${errorMessage}</p>
		<form method="post" action="LoginServlet">
			<div class="form-group row">
				<label for="loginId" class="col-sm-2 col-form-label"><b>ログインID</b></label>
				<div class="col-sm-10">
					<input type="text" name="loginId" id="loginId" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label for="password" class="col-sm-2 col-form-label"><b>パスワード</b></label>
				<div class="col-sm-10">
					<input type="password" name="password" id="password" class="form-control">
				</div>
			</div>

			<div class="text-center">
			<button type="submit" class="btn btn-primary">ログイン</button>
			</div>
		</form>
	</div>
</body>
</html>