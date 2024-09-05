package br.edu.ifsp.arq.ads.brotinho.model.entities;

public class User {

	private Long id = Long.valueOf(0);
	private String name;
	private String email;
	private String password;
	
	public Boolean getIsLogged() {
		return id.intValue() > 0;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
