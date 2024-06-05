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

import gmail.davidsousalves.dto.FornecedorDTO;
import gmail.davidsousalves.services.FornecedorService;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<FornecedorDTO>> buscarTodosClientes() {
		List<FornecedorDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id) {
		FornecedorDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/fornecedor-paginados")
	public ResponseEntity<Page<FornecedorDTO>> buscaPaginada(Pageable pageable) {
	    Page<FornecedorDTO> fornecedorDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(fornecedorDTOPage);
	}

	@PostMapping
	public ResponseEntity<FornecedorDTO> create(@RequestBody FornecedorDTO fornecedorDto) {
		service.create(fornecedorDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FornecedorDTO> update(@PathVariable Long id, @RequestBody FornecedorDTO fornecedorDto) {
		fornecedorDto = service.update(id, fornecedorDto);
		return ResponseEntity.ok(fornecedorDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
