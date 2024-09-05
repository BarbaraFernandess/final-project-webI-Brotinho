<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Brotinho - Receitas Saudáveis</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/home.css">
<link rel="icon" href="img/logo.png">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="banner-container">
		<img src="img/background-home.png" alt="banner - comidas saudáveis"
			id="banner">
	</div>
	<div class="categories">
		<div class="category-item">
			<a href="list-recipes.jsp?search=lactose"> <img
				src="img/categories/sem-lactose.png" alt="Receitas sem leite">
			</a>
			<p>SEM LACTOSE</p>
		</div>
		<div class="category-item">
			<a href="list-recipes.jsp?search=gluten"> <img
				src="img/categories/sem-gluten.png" alt="Receitas sem glúten">
			</a>
			<p>SEM GLÚTEN</p>
		</div>
		<div class="category-item">
			<a href="list-recipes.jsp?search=salada"> <img
				src="img/categories/salada.png" alt="Saladas">
			</a>
			<p>SALADAS</p>
		</div>
		<div class="category-item">
			<a href="list-recipes.jsp?search=vegan"> <img
				src="img/categories/vegana.png" alt="Receitas veganas">
			</a>
			<p>VEGANAS</p>
		</div>
		<div class="category-item">
			<a href="list-recipes.jsp?search=sobremesa"> <img
				src="img/categories/sobremesa.jpg" alt="Sobremesas">
			</a>
			<p>SOBREMESAS</p>
		</div>
	</div>
	<div class="main-container">
		<h1>Nossas receitas mais acessadas!</h1>
		<div class="recipes">
			<c:forEach var="recipe" items="${recipes}">
				<a href="recipe.jsp?id=${recipe.id}" class="recipe-link">
					<div class="recipe-card">
						<img src="${recipe.cover}" alt="${recipe.name}"
							class="recipe-image">
						<div class="recipe-content">
							<h2 class="recipe-category">RECEITA</h2>
							<h3 class="recipe-title">${recipe.name}</h3>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
</body>
</html>
