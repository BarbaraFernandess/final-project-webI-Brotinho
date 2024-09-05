function likeRecipe(){
	var heart = document.getElementById('favorite-icon');
	
	if (heart.className == "bi bi-heart"){
		heart.className = "bi bi-heart-fill";
	} else{
		heart.className = "bi bi-heart";
	}
}