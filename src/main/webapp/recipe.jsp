<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receita ${recipe.id} | Brotinho</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/recipe.css">
<link rel="icon" href="img/logo.png">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="recipe-container">
		<h1>${recipe.name}</h1>
		<img src="${recipe.cover}" alt="${recipe.name}">
		<div class="info">
			<div class="info-item">
				<i class="bi bi-clock"></i> <span>${recipe.duration} Min</span>
			</div>
			<div class="info-item">
				<i class="bi bi-bar-chart"></i> <span>${recipe.verboseLevel}</span>
			</div>
			<form action="ControllerServlet" method="post" id="favorite">
				<input type="hidden" name="recipe_id" value="${recipe.id}">
				<c:choose>
					<c:when test="${recipe.favorited}">
						<input type="hidden" name="favorite_action" value="delete">
					</c:when>
					<c:otherwise>
						<input type="hidden" name="favorite_action" value="save">
					</c:otherwise>
				</c:choose>
				<button id="favorite-button" type="submit" name="action" value="favorite" form="favorite">
				<c:choose>
					<c:when test="${!user.isLogged}">
						<span></span>
					</c:when>
					<c:when test="${recipe.favorited}">
						<i class="bi bi-heart-fill" id="favorite-icon"></i> <span>DESFAVORITAR</span>
					</c:when>
					<c:otherwise>
						<i class="bi bi-heart" id="favorite-icon"></i> <span>FAVORITAR</span>
					</c:otherwise>
				</c:choose>
				</button>
			</form>
		</div>
		<div id="tag-container">
			<c:forEach var="tag" items="${recipe.tags}">
				<span>${tag}</span>
			</c:forEach>
		</div>
		<p>${recipe.description}</p>
		<h2>Ingredientes</h2>
		<ul>
			<c:forEach var="obj" items="${recipe.ingredients}">
				<li>${obj}</li>
			</c:forEach>
		</ul>
		<h2>Modo de Preparo</h2>
		<ol>
			<c:forEach var="obj" items="${recipe.steps}">
				<li>${obj}</li>
			</c:forEach>
		</ol>
	</div>
</body>
<script type="text/javascript" src="js/favorite-button.js"></script>
</html>