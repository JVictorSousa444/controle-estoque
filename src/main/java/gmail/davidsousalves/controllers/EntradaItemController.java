package gmail.davidsousalves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import gmail.davidsousalves.services.EntradaItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entrada-item")
public class EntradaItemController {

	@Autowired
	private EntradaItemService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<EntradaItemDTO>> buscarTodos() {
		List<EntradaItemDTO> entradaItemDTO = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(entradaItemDTO);
	}
	
	@GetMapping("/entrada-items-paginados")
	public ResponseEntity<Page<EntradaItemDTO>> buscaPaginada(Pageable pageable) {
	    Page<EntradaItemDTO> entradaItemDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(entradaItemDTOPage);
	}


	@GetMapping("/{id}")
	public ResponseEntity<EntradaItemDTO> findById(@PathVariable Long id) {
		EntradaItemDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<EntradaItemDTO> create(@Valid @RequestBody EntradaItemDTO entradaItemDto) {
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
