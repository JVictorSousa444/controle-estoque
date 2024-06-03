package gmail.davidsousalves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmail.davidsousalves.services.AuthService;
import gmail.davidsousalves.vo.v1.security.AccountCredentialsVO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if (authService.checkIfParamsIsNotNull(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de cliente inválida!!");
		ResponseEntity token = authService.signin(data);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de cliente inválida!!");
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		if (authService.checkIfParamsIsNotNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de cliente inválida!!");
		ResponseEntity token = authService.refreshToken(username, refreshToken);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de cliente inválida!!");
		return token;
	}

}
