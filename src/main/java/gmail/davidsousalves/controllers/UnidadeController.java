package gmail.davidsousalves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import gmail.davidsousalves.model.Unidade;
import gmail.davidsousalves.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

	@Autowired
	private UnidadeService service;

	@GetMapping
	public ResponseEntity<List<Unidade>> getAllUnidade() {
		List<Unidade> unidade = service.findAll();
		return ResponseEntity.ok(unidade);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UnidadeDTO> findById(@PathVariable Long id) {
		UnidadeDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
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
