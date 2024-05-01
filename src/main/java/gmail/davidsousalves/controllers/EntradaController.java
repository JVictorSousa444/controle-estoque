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

import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.services.EntradaService;

@RestController
@RequestMapping("/entrada")
public class EntradaController {

	@Autowired
	private EntradaService service;
	
	@GetMapping
	public ResponseEntity<List<Entrada>> getAllUnidade() {
		List<Entrada> entrada = service.findAll();
		return ResponseEntity.ok(entrada);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Entrada> findById(@PathVariable Long id) {
		Optional<Entrada> entrada = service.findById(id);
		if (entrada.isPresent()) {
			return ResponseEntity.ok(entrada.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Entrada> create(@RequestBody Entrada entrada) {
		Entrada savedEntrada = service.save(entrada);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntrada);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Entrada> update(@PathVariable Long id, @RequestBody Entrada entrada) {
		Optional<Entrada> existingEntrada = service.findById(id);

		if (existingEntrada.isPresent()) {
			entrada.setId(id);
			Entrada updatedEntrada = service.save(entrada);
			return ResponseEntity.ok(updatedEntrada);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Entrada> entrada = service.findById(id);
		if (entrada.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
