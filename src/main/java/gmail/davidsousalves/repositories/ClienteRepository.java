package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
