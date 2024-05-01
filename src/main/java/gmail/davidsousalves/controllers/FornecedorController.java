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

import gmail.davidsousalves.model.Fornecedor;
import gmail.davidsousalves.services.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService service;
	
	@GetMapping
	public ResponseEntity<List<Fornecedor>> getAllUnidade() {
		List<Fornecedor> fornecedor = service.findAll();
		return ResponseEntity.ok(fornecedor);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = service.findById(id);
		if (fornecedor.isPresent()) {
			return ResponseEntity.ok(fornecedor.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor fornecedor) {
		Fornecedor savedFornecedor = service.save(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFornecedor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
		Optional<Fornecedor> existingFornecedor = service.findById(id);

		if (existingFornecedor.isPresent()) {
			fornecedor.setId(id);
			Fornecedor updatedFornecedor = service.save(fornecedor);
			return ResponseEntity.ok(updatedFornecedor);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = service.findById(id);
		if (fornecedor.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
