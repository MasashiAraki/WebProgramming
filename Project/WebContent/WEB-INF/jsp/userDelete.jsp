<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ削除確認</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/origin/common.css">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark mb-3">
		<a class="text-white">${userInfo.name}さん</a>
		<a href="LogoutServlet" class="text-danger font-weight-bold">ログアウト</a>

	</nav>

	<div class="container">
		<p class="h1 mb-5 font-weight-bold text-center">ユーザ削除確認</p>

		<div class="text-center">
			<p>ログインID：${user.loginId}</p>
			<p>を本当に削除してよろしいでしょうか。</p>

		</div>

		<form method="post" action="UserDeleteServlet">
			<input type="hidden" name="loginId" value="${user.loginId }">
			<div class="text-center">
				<button type="submit" class="btn btn-primary">削除</button>
				<a class="btn btn-secondary" href="UserListServlet" role="button">戻る</a>
			</div>
		</form>

	</div>
</body>
</html>