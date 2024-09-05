package br.edu.ifsp.arq.ads.brotinho.model.entities;

import java.util.List;

public class Recipe {
	private Long id;
	private String name;
	private String description;
	private List<String> steps;
	private List<String> ingredients;
	private int duration;
	private int level;
	private String verboseLevel;
	private String cover;
	private List<String> tags;
	private Boolean favorited;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getLevel() {
		return level;
	}
	public String getVerboseLevel() {
		return verboseLevel;
	}
	public void setLevel(int level) {
		this.level = level;
		switch(this.level) {
			case 1:
				this.verboseLevel = "Fácil";
				break;
			case 2:
				this.verboseLevel = "Médio";
				break;
			default:
				this.verboseLevel = "Díficil";
				break;
		}
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public Boolean getFavorited() {
		return favorited;
	}
	public void setFavorited(Boolean favorited) {
		this.favorited = favorited;
	}
}