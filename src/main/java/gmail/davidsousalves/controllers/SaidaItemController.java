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

import gmail.davidsousalves.dto.SaidaItemDTO;
import gmail.davidsousalves.services.SaidaItemService;

@RestController
@RequestMapping("/saidas-itens")
public class SaidaItemController {
	
	@Autowired
	private SaidaItemService service;
	
	@GetMapping("/busca-todos")
	public ResponseEntity<List<SaidaItemDTO>> buscarTodosClientes() {
		List<SaidaItemDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaidaItemDTO> findById(@PathVariable Long id) {
		SaidaItemDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/saida-items-paginados")
	public ResponseEntity<Page<SaidaItemDTO>> buscaPaginada(Pageable pageable) {
	    Page<SaidaItemDTO> saidaItemDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(saidaItemDTOPage);
	}

	@PostMapping
	public ResponseEntity<SaidaItemDTO> create(@RequestBody SaidaItemDTO saidaItemDto) {
		service.create(saidaItemDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saidaItemDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaidaItemDTO> update(@PathVariable Long id, @RequestBody SaidaItemDTO saidaItemDto) {
		saidaItemDto = service.update(id, saidaItemDto);
		return ResponseEntity.ok(saidaItemDto);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
