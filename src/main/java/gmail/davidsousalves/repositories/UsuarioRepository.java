package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gmail.davidsousalves.security.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.userName =:userName")
	Usuario findByUsername(@Param("userName") String userName);
}