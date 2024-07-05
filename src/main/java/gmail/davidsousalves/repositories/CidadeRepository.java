package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.Cidade;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

    @Query( value = "SELECT * FROM CIDADE C WHERE C.NOME LIKE %:nome% limit 20" ,
            nativeQuery = true)
    List<Cidade> findByNome(String nome);
}
