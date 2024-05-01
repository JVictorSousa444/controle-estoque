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
	public ResponseEntity<Unidade> findById(@PathVariable Long id) {
		Optional<Unidade> unidade = service.findById(id);
		if (unidade.isPresent()) {
			return ResponseEntity.ok(unidade.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Unidade> create(@RequestBody Unidade unidade) {
		Unidade savedUnidade = service.create(unidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUnidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Unidade> update(@PathVariable Long id, @RequestBody Unidade unidade) {
		Optional<Unidade> existingUnidade = service.findById(id);

		if (existingUnidade.isPresent()) {
			unidade.setId(id);
			Unidade updatedUnidade = service.create(unidade);
			return ResponseEntity.ok(updatedUnidade);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Unidade> unidade = service.findById(id);
		if (unidade.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
}
