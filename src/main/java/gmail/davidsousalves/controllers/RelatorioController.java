package gmail.davidsousalves.controllers;

import gmail.davidsousalves.dto.RelatorioDTO;
import gmail.davidsousalves.dto.RelatorioFiltroDTO;
import gmail.davidsousalves.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("contas-a-receber")
    public ResponseEntity<Page<RelatorioDTO>> buscarContasAReceber(RelatorioFiltroDTO filtro, Pageable pageable) {
        Page<RelatorioDTO> resultado = relatorioService.buscarContasAReceber(filtro, pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("contas-a-pagar")
    public ResponseEntity<Page<RelatorioDTO>> buscarContasAPagar(RelatorioFiltroDTO filtro, Pageable pageable) {
        Page<RelatorioDTO> resultado = relatorioService.buscarContasAPagar(filtro, pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("produtos")
    public ResponseEntity<Page<RelatorioDTO>> produtos(RelatorioFiltroDTO filtro, Pageable pageable) {
        Page<RelatorioDTO> resultado = relatorioService.produtos(filtro, pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("entrada-e-saida-por-mes")
    public ResponseEntity<List<RelatorioDTO>> buscarTotalEntradaESaidaPorMes(RelatorioFiltroDTO filtro) {
        List<RelatorioDTO> resultado = relatorioService.buscarTotalEntradaESaidaPorMes(filtro);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("rank-quantidade-produto-vendidos-por-mes")
    public ResponseEntity<List<RelatorioDTO>> rankQuantidadeDeProdutosVendidosNoMes(RelatorioFiltroDTO filtro) {
        List<RelatorioDTO> resultado = relatorioService.rankQuantidadeDeProdutosVendidosNoMes(filtro);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("valor-total-entrada-e-saida-no-mes")
    public ResponseEntity<List<RelatorioDTO>> valorTotalDeEntradaESaidaNoMes(RelatorioFiltroDTO filtro) {
        List<RelatorioDTO> resultado = relatorioService.valorTotalDeEntradaESaidaNoMes(filtro);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("quantidade-contas-a-receber-a-vencer")
    public ResponseEntity<Integer> quantidadeContasAReceberVencidas(RelatorioFiltroDTO filtro) {
        Integer resultado = relatorioService.quantidadeContasAReceberVencidas(filtro);
        return ResponseEntity.ok(resultado);
    }
}
