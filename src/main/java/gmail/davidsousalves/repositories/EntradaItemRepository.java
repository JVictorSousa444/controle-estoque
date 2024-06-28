package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.EntradaItem;

import java.util.List;

public interface EntradaItemRepository extends JpaRepository<EntradaItem, Long> {

    List<EntradaItem> findByEntradaId(Long entradaId);
}
