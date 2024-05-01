package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
