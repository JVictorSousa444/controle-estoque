package gmail.davidsousalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gmail.davidsousalves.model.SaidaItem;

import java.util.List;

public interface SaidaItemRepository extends JpaRepository<SaidaItem, Long>{

    List<SaidaItem> findBySaidaId(Long saidaId);
}
