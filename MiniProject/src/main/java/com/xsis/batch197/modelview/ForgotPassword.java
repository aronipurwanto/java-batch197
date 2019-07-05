package com.xsis.batch197.modelview;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ForgotPassword {
	@Email(message = "Format harus email")
	@NotEmpty(message = "Tidak boleh kosong")
	@Size(min = 5)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
