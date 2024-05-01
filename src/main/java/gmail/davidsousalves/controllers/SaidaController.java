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

import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.services.SaidaService;

@RestController
@RequestMapping("/saida")
public class SaidaController {

	@Autowired
	private SaidaService service;
	
	@GetMapping
	public ResponseEntity<List<Saida>> getAllUnidade() {
		List<Saida> saida = service.findAll();
		return ResponseEntity.ok(saida);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Saida> findById(@PathVariable Long id) {
		Optional<Saida> saida = service.findById(id);
		if (saida.isPresent()) {
			return ResponseEntity.ok(saida.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Saida> create(@RequestBody Saida saida) {
		Saida savedSaida = service.save(saida);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSaida);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Saida> update(@PathVariable Long id, @RequestBody Saida saida) {
		Optional<Saida> existingSaida = service.findById(id);

		if (existingSaida.isPresent()) {
			saida.setId(id);
			Saida updatedSaida = service.save(saida);
			return ResponseEntity.ok(updatedSaida);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Saida> entrada = service.findById(id);
		if (entrada.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
