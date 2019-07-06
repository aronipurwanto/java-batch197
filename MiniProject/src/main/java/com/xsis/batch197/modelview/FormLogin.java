package com.xsis.batch197.modelview;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FormLogin {
	@NotEmpty(message = "Username tidak boleh kosong")
	@Size(min = 5, max=20)
	private String username;
	
	@NotEmpty(message = "Username tidak boleh kosong")
	@Size(min = 5, max=20)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
