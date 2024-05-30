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

import gmail.davidsousalves.dto.UnidadeDTO;
import gmail.davidsousalves.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

	@Autowired
	private UnidadeService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<UnidadeDTO>> buscarTodosClientes() {
		List<UnidadeDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UnidadeDTO> findById(@PathVariable Long id) {
		UnidadeDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping()
	public ResponseEntity<Page<UnidadeDTO>> buscaPaginada(Pageable pageable) {
	    Page<UnidadeDTO> unidadeDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(unidadeDTOPage);
	}

	@PostMapping
	public ResponseEntity<UnidadeDTO> create(@RequestBody UnidadeDTO unidade) {

		service.create(unidade);

		return ResponseEntity.status(HttpStatus.CREATED).body(unidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UnidadeDTO> update(@PathVariable Long id, @RequestBody UnidadeDTO unidade) {
		unidade = service.update(id, unidade);
		return ResponseEntity.ok(unidade);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
