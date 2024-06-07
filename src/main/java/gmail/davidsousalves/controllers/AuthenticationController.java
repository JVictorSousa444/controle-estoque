package gmail.davidsousalves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmail.davidsousalves.exceptions.GenerateTokenException;
import gmail.davidsousalves.repositories.UserRepository;
import gmail.davidsousalves.security.jwt.TokenProvider;
import gmail.davidsousalves.security.model.usuario.AuthenticationDTO;
import gmail.davidsousalves.security.model.usuario.LoginResponseDTO;
import gmail.davidsousalves.security.model.usuario.RegisterDTO;
import gmail.davidsousalves.security.model.usuario.User;
import jakarta.validation.Valid;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenProvider tokenProvider;

	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
	    try {
	        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
	        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

	        String token = tokenProvider.generateToken((User) auth.getPrincipal());

	        return ResponseEntity.ok(new LoginResponseDTO(token));
	    } catch (Exception e) {
	         throw new GenerateTokenException("Erro ao criar");
	    }
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if (this.repository.findByLogin(data.login()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());

		this.repository.save(newUser);

		return ResponseEntity.ok().build();
	}

}