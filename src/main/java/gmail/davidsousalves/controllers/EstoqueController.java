package gmail.davidsousalves.controllers;

import java.util.List;
import java.util.Optional;

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

import gmail.davidsousalves.model.Estoque;
import gmail.davidsousalves.services.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueService service;
	
	@GetMapping
	public ResponseEntity<List<Estoque>> getAllUnidade() {
		List<Estoque> estoque = service.findAll();
		return ResponseEntity.ok(estoque);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estoque> findById(@PathVariable Long id) {
		Optional<Estoque> estoque = service.findById(id);
		if (estoque.isPresent()) {
			return ResponseEntity.ok(estoque.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Estoque> create(@RequestBody Estoque estoque) {
		Estoque savedEstoque = service.save(estoque);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEstoque);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estoque> update(@PathVariable Long id, @RequestBody Estoque estoque) {
		Optional<Estoque> existingEstoque = service.findById(id);

		if (existingEstoque.isPresent()) {
			estoque.setId(id);
			Estoque updatedEstoque = service.save(estoque);
			return ResponseEntity.ok(updatedEstoque);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Estoque> estoque = service.findById(id);
		if (estoque.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
