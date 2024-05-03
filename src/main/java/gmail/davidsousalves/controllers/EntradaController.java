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

import gmail.davidsousalves.dto.EntradaDTO;
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
	public ResponseEntity<EntradaDTO> findById(@PathVariable Long id) {
		EntradaDTO entradaDto = service.findById(id);
		return ResponseEntity.ok(entradaDto);
	}

	@PostMapping
	public ResponseEntity<EntradaDTO> create(@RequestBody EntradaDTO entradaDto) {
		service.create(entradaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(entradaDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntradaDTO> update(@PathVariable Long id, @RequestBody EntradaDTO entradaDto) {
		service.update(id, entradaDto);
		return ResponseEntity.ok(entradaDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
