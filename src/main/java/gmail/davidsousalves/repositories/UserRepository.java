package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import gmail.davidsousalves.security.model.usuario.User;

public interface UserRepository extends JpaRepository<User, Long> {
	UserDetails findByLogin(String login);
}