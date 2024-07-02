package gmail.davidsousalves.repositories.custom;

import gmail.davidsousalves.dto.RelatorioDTO;
import gmail.davidsousalves.dto.RelatorioFiltroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatporioCustomRepository {

    Page<RelatorioDTO> buscarContasAReceber(RelatorioFiltroDTO filtro, Pageable pageable);

    Page<RelatorioDTO> buscarContasAPagar(RelatorioFiltroDTO filtro, Pageable pageable);

    List<RelatorioDTO> buscarTotalEntradaESaidaPorMes(RelatorioFiltroDTO filtro);

    List<RelatorioDTO> rankQuantidadeDeProdutosVendidosNoMes(RelatorioFiltroDTO filtro);

    List<RelatorioDTO> valorTotalDeEntradaESaidaNoMes(RelatorioFiltroDTO filtro);

    Integer quantidadeContasAReceberVencidas(RelatorioFiltroDTO filtro);

    Page<RelatorioDTO> produtos(RelatorioFiltroDTO filtro, Pageable pageable);

    Page<RelatorioDTO> produtosComMenosQuantidadeEstoque(RelatorioFiltroDTO filtro, Pageable pageable);

}
