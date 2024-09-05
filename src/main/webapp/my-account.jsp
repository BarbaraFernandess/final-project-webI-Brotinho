<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minha conta</title>
<link rel="stylesheet" href="css/my-account.css">
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<link rel="icon" href="img/logo.png">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="profile-container">
		<i class="bi bi-person-circle user-icon-large"></i>
		<h2>Meu Perfil</h2>
		<p>Gerenciar e proteger sua conta</p>
		<form id="form-container">
			<div class="form-group">
				<label for="username">Nome de usuário</label> <input type="text"
					id="username" value="${user.name}" disabled>
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email" id="email"
					value="${user.email}" disabled>
			</div>
			<div class="form-group">
				<label for="phone">Senha</label> <input type="password"
					id="password" value="*********" disabled>
			</div>
		</form>
	</div>
</body>
</html>
