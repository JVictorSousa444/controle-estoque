package gmail.davidsousalves.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.Status;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c WHERE lower(c.nome) LIKE lower(concat('%', :nome, '%')) AND (:status IS NULL OR c.status = :status)")
	List<Cliente> findByNomeAndStatus(@Param("nome") String nome, @Param("status") Status status);

	Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
 
}
