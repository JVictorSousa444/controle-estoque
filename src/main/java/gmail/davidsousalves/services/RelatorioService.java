package gmail.davidsousalves.services;

import gmail.davidsousalves.dto.RelatorioDTO;
import gmail.davidsousalves.dto.RelatorioFiltroDTO;
import gmail.davidsousalves.repositories.custom.RelatporioCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private RelatporioCustomRepository relatporioCustomRepository;

    public Page<RelatorioDTO> buscarContasAReceber(RelatorioFiltroDTO filtro, Pageable pageable) {
        return relatporioCustomRepository.buscarContasAReceber(filtro, pageable);
    }

    public Page<RelatorioDTO> buscarContasAPagar(RelatorioFiltroDTO filtro, Pageable pageable) {
        return relatporioCustomRepository.buscarContasAPagar(filtro, pageable);
    }

    public Page<RelatorioDTO> produtos(RelatorioFiltroDTO filtro, Pageable pageable) {
        return relatporioCustomRepository.produtos(filtro, pageable);
    }

    public List<RelatorioDTO> buscarTotalEntradaESaidaPorMes(RelatorioFiltroDTO filtro) {
        return relatporioCustomRepository.buscarTotalEntradaESaidaPorMes(filtro);
    }

    public List<RelatorioDTO> rankQuantidadeDeProdutosVendidosNoMes(RelatorioFiltroDTO filtro) {
        return relatporioCustomRepository.rankQuantidadeDeProdutosVendidosNoMes(filtro);
    }

    public List<RelatorioDTO>  valorTotalDeEntradaESaidaNoMes(RelatorioFiltroDTO filtro) {
        return relatporioCustomRepository.valorTotalDeEntradaESaidaNoMes(filtro);
    }

    public Integer quantidadeContasAReceberVencidas(RelatorioFiltroDTO filtro) {
        return relatporioCustomRepository.quantidadeContasAReceberVencidas(filtro);
    }

    public Page<RelatorioDTO> produtosComMenosQuantidadeEstoque(RelatorioFiltroDTO filtro, Pageable pageable) {
        return relatporioCustomRepository.produtosComMenosQuantidadeEstoque(filtro, pageable);
    }

}
