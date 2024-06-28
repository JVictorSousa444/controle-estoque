package gmail.davidsousalves.services;

import gmail.davidsousalves.dto.RelatorioDTO;
import gmail.davidsousalves.dto.RelatorioFiltroDTO;
import gmail.davidsousalves.repositories.custom.RelatporioCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private RelatporioCustomRepository relatporioCustomRepository;

    public Page<RelatorioDTO> buscarContasAReceber(RelatorioFiltroDTO filtro, Pageable pageable) {
        return relatporioCustomRepository.buscarContasAReceber(filtro, pageable);
    }

}
