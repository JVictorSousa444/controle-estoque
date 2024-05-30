package gmail.davidsousalves.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

    Page<Fornecedor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
