package gmail.davidsousalves.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.repositories.UsuarioRepository;
import gmail.davidsousalves.security.jwt.JwtTokenProvider;
import gmail.davidsousalves.security.model.Usuario;
import gmail.davidsousalves.vo.v1.security.AccountCredentialsVO;
import gmail.davidsousalves.vo.v1.security.TokenVO;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UsuarioRepository repository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
			String username = data.getUsername();
			String password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			Usuario user = repository.findByUsername(username);

			TokenVO tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Nome de usuario " + username + " não encontrado!");
			}
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("Nome de usuário/senha inválidos fornecidos!");
		}
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		Usuario user = repository.findByUsername(username);

		TokenVO tokenResponse = new TokenVO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Nome de usuario " + username + " não encontrado!");
		}
		return ResponseEntity.ok(tokenResponse);
	}
	
	public boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank()
				 || data.getPassword() == null || data.getPassword().isBlank();
	}
	
	public boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() ||
				username == null || username.isBlank();
	}

}
