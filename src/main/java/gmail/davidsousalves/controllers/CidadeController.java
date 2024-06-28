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

import gmail.davidsousalves.dto.CidadeDTO;
import gmail.davidsousalves.services.CidadeService;

@RestController
@RequestMapping("api/cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<CidadeDTO>> buscarTodos() {
		List<CidadeDTO> cidadeDTO = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
		CidadeDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/estoque-paginados")
	public ResponseEntity<Page<CidadeDTO>> buscaPaginada(Pageable pageable) {
	    Page<CidadeDTO> cidadeDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(cidadeDTOPage);
	}

	@PostMapping
	public ResponseEntity<CidadeDTO> create(@RequestBody CidadeDTO cidadeDTO) {
		
		CidadeDTO createCidade = service.create(cidadeDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(createCidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CidadeDTO> update(@PathVariable Long id, @RequestBody CidadeDTO cidadeDTO) {
		service.update(id, cidadeDTO);

		return ResponseEntity.ok(cidadeDTO);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
}
