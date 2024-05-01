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

import gmail.davidsousalves.model.SaidaItem;
import gmail.davidsousalves.services.SaidaItemService;

@RestController
@RequestMapping("/saida-item")
public class SaidaItemController {
	
	@Autowired
	private SaidaItemService service;
	
	@GetMapping
	public ResponseEntity<List<SaidaItem>> getAllUnidade() {
		List<SaidaItem> saida = service.findAll();
		return ResponseEntity.ok(saida);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaidaItem> findById(@PathVariable Long id) {
		Optional<SaidaItem> saida = service.findById(id);
		if (saida.isPresent()) {
			return ResponseEntity.ok(saida.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<SaidaItem> create(@RequestBody SaidaItem saidaItem) {
		SaidaItem savedSaida = service.save(saidaItem);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSaida);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaidaItem> update(@PathVariable Long id, @RequestBody SaidaItem saidaItem) {
		Optional<SaidaItem> existingSaidaItem = service.findById(id);

		if (existingSaidaItem.isPresent()) {
			saidaItem.setId(id);
			SaidaItem updatedSaidaItem = service.save(saidaItem);
			return ResponseEntity.ok(updatedSaidaItem);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<SaidaItem> entrada = service.findById(id);
		if (entrada.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
}
