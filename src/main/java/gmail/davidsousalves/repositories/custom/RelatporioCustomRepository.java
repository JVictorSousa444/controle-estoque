package gmail.davidsousalves.repositories.custom;

import gmail.davidsousalves.dto.RelatorioDTO;
import gmail.davidsousalves.dto.RelatorioFiltroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatporioCustomRepository {

    Page<RelatorioDTO> buscarContasAReceber(RelatorioFiltroDTO filtro, Pageable pageable);
}
