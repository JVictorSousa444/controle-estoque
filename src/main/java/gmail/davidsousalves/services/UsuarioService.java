package gmail.davidsousalves.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.repositories.UsuarioRepository;
import gmail.davidsousalves.security.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Usuario user = repository.findByUsername(username);
		
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Nome de usuario " + username + " não encontrado!");
		}

	}

}
