package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gmail.davidsousalves.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

}
	
