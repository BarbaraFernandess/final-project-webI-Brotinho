<script>
	function makeSearch() {
		const searchInput = document.getElementById("search-input");
		const query = !!searchInput.value && searchInput.value != '' ? '?search='
				+ searchInput.value
				: ''
		window.location.href = "list-recipes.jsp" + query;
	}
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="navbar">
	<a href="home.jsp" id="icon-navbar"> <img alt="logo"
		src="img/logo.png" id="logo">
		<h3>BROTINHO</h3>
	</a>
	<div class="nav-search">
		<i class="bi bi-search"></i> <input id="search-input" type="text"
			placeholder="Insira uma receita, um ingrediente.." />
		<button id="search-button" onclick="makeSearch()">Procurar</button>
	</div>
	<div class="dropdown">
		<button class="dropbtn">
			<i class="bi bi-person-circle" id="user-icon"></i>
		</button>
		<div class="dropdown-content">
			<c:choose>
				<c:when test="${user.isLogged}">
					<a href="my-account.jsp">MINHA CONTA</a>
					<a href="list-recipes.jsp?fav=true">RECEITAS FAVORITAS</a>
					<form action="ControllerServlet" method="post" id="logout">
						<button id="logout" type="submit" name="action" value="logout"
							form="logout">SAIR</button>
					</form>
				</c:when>
				<c:otherwise>
					<a href="login.jsp">ENTRAR</a>
					<a href="user-register.jsp">CRIAR CONTA</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>