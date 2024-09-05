<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receitas - Brotinho</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/list-recipes.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<link rel="icon" href="img/logo.png">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="main-container">
		<c:choose>
			<c:when test="${fav}">
				<h2 id="title">
					<i class="bi bi-stars"></i>
					Receitas Favoritas
				</h2>
				<p id="subtitle">Aqui estão as suas receitas favoritadas.</p>
			</c:when>
			<c:otherwise>
				<h2 id="title">Listagem Receitas</h2>
				<p id="subtitle">Aqui estão as receitas que correspondem à sua
					pesquisa.</p>
			</c:otherwise>
		</c:choose>
		<div class="recipes">
			<c:forEach var="recipe" items="${recipes}">
				<a href="recipe.jsp?id=${recipe.id}" class="recipe-link">
					<div class="recipe-card">
						<img src="${recipe.cover}"
							alt="${recipe.name}"
							class="recipe-image">
						<div class="recipe-content">
							<h2 class="recipe-category">
							<c:if test="${recipe.favorited}">
								<i class="bi bi-stars"></i>
							</c:if>
								RECEITAS
							</h2>
							<h3 class="recipe-title">${recipe.name}</h3>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
</body>
</html>