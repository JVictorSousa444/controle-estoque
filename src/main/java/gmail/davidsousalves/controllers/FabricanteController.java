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

import gmail.davidsousalves.model.Fabricante;
import gmail.davidsousalves.services.FabricanteService;

@RestController
@RequestMapping("/fabricante")
public class FabricanteController {

	@Autowired
	private FabricanteService service;
	
	@GetMapping
	public ResponseEntity<List<Fabricante>> getAllUnidade() {
		List<Fabricante> fabricante = service.findAll();
		return ResponseEntity.ok(fabricante);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fabricante> findById(@PathVariable Long id) {
		Optional<Fabricante> fabricante = service.findById(id);
		if (fabricante.isPresent()) {
			return ResponseEntity.ok(fabricante.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Fabricante> create(@RequestBody Fabricante fabricante) {
		Fabricante savedFabricante = service.save(fabricante);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFabricante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Fabricante> update(@PathVariable Long id, @RequestBody Fabricante fabricante) {
		Optional<Fabricante> existingFabricante = service.findById(id);

		if (existingFabricante.isPresent()) {
			fabricante.setId(id);
			Fabricante updatedFabricante = service.save(fabricante);
			return ResponseEntity.ok(updatedFabricante);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Fabricante> fabricante = service.findById(id);
		if (fabricante.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}

}
