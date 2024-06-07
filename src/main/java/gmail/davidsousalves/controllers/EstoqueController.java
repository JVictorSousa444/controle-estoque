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

import gmail.davidsousalves.dto.EstoqueDTO;
import gmail.davidsousalves.services.EstoqueService;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<EstoqueDTO>> buscarTodosClientes() {
		List<EstoqueDTO> estoqueDTO = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(estoqueDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstoqueDTO> findById(@PathVariable Long id) {
		EstoqueDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/estoque-paginados")
	public ResponseEntity<Page<EstoqueDTO>> buscaPaginada(Pageable pageable) {
	    Page<EstoqueDTO> estoqueDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(estoqueDTOPage);
	}

	@PostMapping
	public ResponseEntity<EstoqueDTO> create(@RequestBody EstoqueDTO estoqueDto) {
		service.create(estoqueDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(estoqueDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstoqueDTO> update(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDto) {
		service.update(id, estoqueDto);

		return ResponseEntity.ok(estoqueDto);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
}
