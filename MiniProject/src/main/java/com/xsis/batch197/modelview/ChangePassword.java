package com.xsis.batch197.modelview;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePassword {
	private Long userId;
	
	private String token;
	
	@NotEmpty(message = "Tidak Boleh Kosong")
	@Size(min = 4, max = 20)
	private String newPassword;
	
	@NotEmpty(message = "Tidak Boleh Kosong")
	@Size(min = 4, max = 20)
	private String retypePassword;

}
