<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="pt-BR">
  <head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300..800;1,300..800&family=Radio+Canada+Big:ital,wght@0,400..700;1,400..700&display=swap" rel="stylesheet">

	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">
    <title>Brotinho - Página de Login</title>
  </head>
  <body>
  <div class="navbar">
	   <div id="icon-navbar">
	   		<img alt="logo" src="img/logo.png" id="logo">
	  		<h3>BROTINHO</h3>
	   </div>	 
	</div>
	<c:choose>
				<c:when test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Usuário cadastrado com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${result == 'loginError'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						E-mail ou senha inválidos.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>
  <div class="main-container">
  	<form action="ControllerServlet" method="post">
  		<h2>Acesse sua conta</h2>
  		<label>Insira seu e-mail:</label>
  		<input type="text" placeholder="exemplo@gmail.com" name="email" id="email">
  		<label>Insira a sua senha:</label>
  		<div class="password-div">
  			<input type="password" id="password" name="password" placeholder="senha">
            <i class="bi bi-eye-slash-fill" id="seepassword" onclick="exibirSenha()"></i>           
  		</div>
  		<button type="submit" name="action" value="login">ENVIAR</button>
  		
  		<span>Não possui conta?
  		<a href="user-register.jsp">
			Cadastre-se aqui
		</a>
  		</span>	
  	</form> 
  </div>	
  </body>
  <script type="text/javascript" src="js/password.js"></script>
</html>
