package br.edu.ifsp.arq.ads.brotinho.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifsp.arq.ads.brotinho.model.entities.Recipe;


public class RecipeDao {
	
	private DataSource dataSource;

	public RecipeDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private String getRecipeQuery(String where, String userId) {
		String queryFavorite = 
				"EXISTS(SELECT 1 FROM user_favorite uf WHERE uf.recipe_id = r.id AND uf.user_id = %s) as favorited ";
		String query = 
				"SELECT " +
			    "    r.id, r.name, r.description, r.steps, r.ingredients, r.duration, r.level, r.cover, " +
			    "    replace(group_concat(t.name), ',', ';') as \"tags\", " +
			    String.format(queryFavorite, userId) +
			    "FROM recipe r " +
			    "LEFT JOIN recipe_tag rt " +
			    "    ON rt.recipe_id = r.id " +
			    "LEFT JOIN tag t " +
			    "    ON rt.tag_id = t.id " +
			    where + " " +
			    "GROUP BY r.id, r.name, r.description, r.steps, r.ingredients, r.duration, r.level, r.cover;";
		return query;
		
	}
	
	private Recipe getRecipeFromResult(ResultSet rs) throws SQLException {
		Recipe r = new Recipe();
		r.setId(rs.getLong("id"));
		r.setName(rs.getString("name"));
		r.setDescription(rs.getString("description"));
		r.setSteps(new ArrayList<>(
			Arrays.asList(rs.getString("steps").split("\\d+\\."))
		));
		r.setIngredients(new ArrayList<>(
			Arrays.asList(rs.getString("ingredients").split(";"))
		));
		r.setDuration(rs.getInt("duration"));
		r.setLevel(rs.getInt("level"));
		r.setCover(rs.getString("cover"));
		r.setFavorited(rs.getBoolean("favorited"));
		
		String tags = rs.getString("tags");
		r.setTags(new ArrayList<>(
				Arrays.asList(tags != null ? tags.split(";") : "".split(""))
		));
		
		return r;
	}
	
	public Recipe getRecipeById(String id, String userId) {
		String sql = this.getRecipeQuery("WHERE r.id = %s", userId);
		sql = String.format(sql, id);
		System.out.println(sql);
		Recipe recipe = null;
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					recipe = this.getRecipeFromResult(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return recipe;
	}

	public List<Recipe> getRecipesByIds(String ids, String userId) {
		String sql = this.getRecipeQuery("WHERE r.id IN (%s)", userId);
		sql = String.format(sql, ids);
		System.out.println(sql);
		List<Recipe> recipes = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					recipes.add(this.getRecipeFromResult(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return recipes;
	}
	
	public List<Recipe> getRecipesBySearch(String search, String userId) {
		String sql = this.getRecipeQuery(
			"WHERE false" +
			" or lower(r.name) like lower('%%s%')" +
			" or lower(r.description) like lower('%%s%')" +
			" or lower(r.steps) like lower('%%s%')" +
			" or lower(r.ingredients) like lower('%%s%')" +
			" or lower(t.name) like lower('%%s%')", 
			userId
		);
		sql = sql.replace("%s", search);
		System.out.println(sql);
		List<Recipe> recipes = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					recipes.add(this.getRecipeFromResult(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return recipes;
	}
	
	public List<Recipe> getFavoriteRecipes(String search, String userId) {
		String condition = "WHERE exists (select 1 from user_favorite uf where uf.recipe_id = r.id and uf.user_id = %s)";
		String stringCondition = 
				" and (lower(r.name) like lower('%%s%')" +
				" or lower(r.description) like lower('%%s%')" +
				" or lower(r.steps) like lower('%%s%')" +
				" or lower(r.ingredients) like lower('%%s%')" +
				" or lower(t.name) like lower('%%s%'))";
		String sql = this.getRecipeQuery(String.format(condition, userId), userId);
		System.out.println(sql);
		List<Recipe> recipes = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					recipes.add(this.getRecipeFromResult(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return recipes;
	} 
	
	public List<Recipe> getAllFavoriteRecipes(String userId) {
		String condition = "WHERE exists (select 1 from user_favorite uf where uf.recipe_id = r.id and uf.user_id = %s)";
		String sql = this.getRecipeQuery(String.format(condition, userId), userId);
		System.out.println(sql);
		List<Recipe> recipes = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					recipes.add(this.getRecipeFromResult(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return recipes;
	} 
	
	public List<Recipe> getAllRecipes(String userId) {
		String sql = this.getRecipeQuery("WHERE true", userId);
		System.out.println(sql);
		List<Recipe> recipes = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					recipes.add(this.getRecipeFromResult(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return recipes;
	}
}
