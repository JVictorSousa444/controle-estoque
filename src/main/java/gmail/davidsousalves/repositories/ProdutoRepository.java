package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.Produto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    @Query(
            value = "update produto set quantidade = (quantidade + :quantidade) where id = :id",
            nativeQuery = true
    )
    @Modifying
    void atualizarQuantidade(Long id, Integer quantidade);

    @Query(
            value = "update produto set quantidade = (quantidade - :quantidade) where id = :id",
            nativeQuery = true
    )
    @Modifying
    void atualizarQuantidadeRemover(Long id, Integer quantidade);
}
