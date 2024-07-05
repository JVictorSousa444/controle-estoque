package gmail.davidsousalves.controllers;

import java.util.List;

import gmail.davidsousalves.vo.EntradaVO;
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

import gmail.davidsousalves.dto.EntradaDTO;
import gmail.davidsousalves.services.EntradaService;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

	@Autowired
	private EntradaService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<EntradaDTO>> buscarTodos() {
		List<EntradaDTO> entradaDTO = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(entradaDTO);
	}
	
	@GetMapping
	public ResponseEntity<Page<EntradaVO>> buscaPaginada(Pageable pageable) {
	    Page<EntradaVO> entradaDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(entradaDTOPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntradaDTO> findById(@PathVariable Long id) {
		EntradaDTO entradaDto = service.findById(id);
		return ResponseEntity.ok(entradaDto);
	}

	@PostMapping
	public ResponseEntity<EntradaDTO> create(@RequestBody EntradaDTO entradaDto) {
		EntradaDTO createEntrada = service.create(entradaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createEntrada);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntradaDTO> update(@PathVariable Long id, @RequestBody EntradaDTO entradaDto) {
		service.update(id, entradaDto);
		return ResponseEntity.ok(entradaDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
