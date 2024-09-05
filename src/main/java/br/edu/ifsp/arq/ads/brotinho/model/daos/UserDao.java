package br.edu.ifsp.arq.ads.brotinho.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.ads.brotinho.model.entities.User;
import br.edu.ifsp.arq.ads.brotinho.utils.PasswordEncode;

public class UserDao {
	
	private DataSource dataSource;

	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Optional<User> getUserByEmailAndPassword(String email, String password) {
		String passwordEncrypted = PasswordEncode.encode(password);
		String sql = "SELECT id, name, email, password FROM user WHERE email=? AND password=?";
		Optional<User> optional = Optional.empty();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, passwordEncrypted);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					optional = Optional.of(user);  // Habilitar esta linha
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return optional;
	}
	
	public Optional<User> getUserByEmail(String email) {
		String sql = "SELECT id, name, email FROM user WHERE email=?";
		Optional<User> optional = Optional.empty();
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					optional = Optional.of(user);  // Habilitar esta linha
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return optional;
	}
	
	public Boolean save(User user) {
		Optional<User> optional = getUserByEmail(user.getEmail());
		if (optional.isPresent()) {
			return false;
		}
		String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); 
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta", e);
		}
		return true;
	}
	
	public Boolean saveUserFavorite(String userId, String recipeId) {
        String sql = "INSERT INTO user_favorite (user_id, recipe_id) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, recipeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar favorito do usuário", e);
        }
        return true;
    }
	
	public Boolean deleteUserFavorite(String userId, String recipeId) {
        String sql = "DELETE FROM user_favorite WHERE user_id = ? and recipe_id = ?";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, recipeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar favorito do usuário", e);
        }
        return true;
    }
	
}
