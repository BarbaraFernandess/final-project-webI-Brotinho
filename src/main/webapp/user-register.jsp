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

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link href="css/user-register.css" rel="stylesheet">
	
    <title>Brotinho - Página de Cadastro de Usuário</title>
  </head>
  <body>
  <div class="navbar">
	   <div id="icon-navbar">
	   		<img alt="logo" src="img/logo.png" id="logo">
	  		<h3>BROTINHO</h3>
	   </div>	 
	</div>
  			<div class="main-container">
  			<c:if test="${result == 'notRegistered'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					E-mail já cadastrado. Tente novamente.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
  				<form action="ControllerServlet" method="post" id="form1">
  				<h1 class="text-center">Cadastre-se</h1>
  			
				  <label for="name">Nome completo*</label>
				  <input type="text" class="" 
				  	name="name" id="name" minlength="3" maxlength="50"
				  	required="required">
				  <span id="0"></span>
				
				
				  <label for="email">E-mail*</label>
				  <input type="email" class=""  
				  	name="email" id="email" required="required">
				  <span id="1"></span>
				
				
				  <label for="password">Senha*</label>
				  <input type="password" class=" "  
				  	name="password" id="password"
				  	minlength="6" maxlength="12"
				  	 required="required">
				  <span id="2"></span>
	
				
				
				  <label for="confirmPassword">Confirmação de Senha*</label>
				  <input type="password" class=""  
				  	name="confirmPassword" id="confirmPassword"
				  	minlength="6" maxlength="12"
				  	 required="required">
				  <span id="3"></span>
				
				
				<button type="submit" name="action" value="addUser">
					Salvar
				</button>
				
  		
  		<span>Já possui conta?
  		<a href="login.jsp">
			Entre aqui
		</a>
  		</span>	
  			</form>
  		</div>
    
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="js/user-register.js"></script>
  </body>
</html>








