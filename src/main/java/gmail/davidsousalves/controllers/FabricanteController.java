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

import gmail.davidsousalves.dto.FabricanteDTO;
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
	public ResponseEntity<FabricanteDTO> findById(@PathVariable Long id) {
		FabricanteDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);

	}

	@PostMapping
	public ResponseEntity<FabricanteDTO> create(@RequestBody FabricanteDTO fabricanteDto) {
		service.create(fabricanteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(fabricanteDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FabricanteDTO> update(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDto) {
		fabricanteDto = service.update(id, fabricanteDto);
        return ResponseEntity.ok(fabricanteDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
