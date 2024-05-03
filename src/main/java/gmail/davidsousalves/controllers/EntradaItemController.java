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

import gmail.davidsousalves.dto.EntradaItemDTO;
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
	public ResponseEntity<EntradaItemDTO> findById(@PathVariable Long id) {
		EntradaItemDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<EntradaItemDTO> create(@RequestBody EntradaItemDTO entradaItemDto) {
		service.create(entradaItemDto);
				
		return ResponseEntity.status(HttpStatus.CREATED).body(entradaItemDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntradaItemDTO> update(@PathVariable Long id, @RequestBody EntradaItemDTO entradaItem) {
		service.update(id, entradaItem);
		return ResponseEntity.ok(entradaItem);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
