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

import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.services.EntradaItemService;

@RestController
@RequestMapping("/entrada-item")
public class EntradaItemController {

	@Autowired
	private EntradaItemService service;
	
	@GetMapping
	public ResponseEntity<List<EntradaItem>> getAllUnidade() {
		List<EntradaItem> entradaItem = service.findAll();
		return ResponseEntity.ok(entradaItem);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntradaItem> findById(@PathVariable Long id) {
		Optional<EntradaItem> entradaItem = service.findById(id);
		if (entradaItem.isPresent()) {
			return ResponseEntity.ok(entradaItem.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<EntradaItem> create(@RequestBody EntradaItem entradaItem) {
		EntradaItem savedEntradaItem = service.save(entradaItem);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntradaItem);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntradaItem> update(@PathVariable Long id, @RequestBody EntradaItem entradaItem) {
		Optional<EntradaItem> existingEntradaItem = service.findById(id);

		if (existingEntradaItem.isPresent()) {
			entradaItem.setId(id);
			EntradaItem updatedEntradaItem = service.save(entradaItem);
			return ResponseEntity.ok(updatedEntradaItem);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<EntradaItem> entradaItem = service.findById(id);
		if (entradaItem.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
