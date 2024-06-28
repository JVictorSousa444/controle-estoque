package gmail.davidsousalves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmail.davidsousalves.dto.SaidaDTO;
import gmail.davidsousalves.services.SaidaService;
import gmail.davidsousalves.vo.SaidaVO;

@RestController
@RequestMapping("/api/saida")
public class SaidaController {

	@Autowired
	private SaidaService service;
	
	@GetMapping("/busca-todos")
	public ResponseEntity<List<SaidaDTO>> buscarTodos() {
		List<SaidaDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaidaDTO> findById(@PathVariable Long id) {
		SaidaDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<SaidaVO>> buscaPaginada(Pageable pageable) {
	    Page<SaidaVO> saidaDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(saidaDTOPage);
	}

	@PostMapping
	public ResponseEntity<SaidaDTO> create(@RequestBody SaidaDTO saidaDto) {
		service.create(saidaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saidaDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaidaDTO> update(@PathVariable Long id, @RequestBody SaidaDTO saidaDto) {
		saidaDto = service.update(id, saidaDto);
		return ResponseEntity.ok(saidaDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
