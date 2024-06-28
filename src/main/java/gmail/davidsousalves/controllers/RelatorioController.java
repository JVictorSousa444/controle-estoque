package gmail.davidsousalves.controllers;

import gmail.davidsousalves.dto.ProdutoDTO;
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
}
