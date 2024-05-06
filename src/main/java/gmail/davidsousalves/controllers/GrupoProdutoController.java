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

import gmail.davidsousalves.dto.GrupoProdutoDTO;
import gmail.davidsousalves.services.GrupoProdutoService;

@RestController
@RequestMapping("/GrupoProduto")
public class GrupoProdutoController {

	@Autowired
	private GrupoProdutoService service;
	
	@GetMapping("/busca-todos")
	public ResponseEntity<List<GrupoProdutoDTO>> buscarTodosClientes() {
		List<GrupoProdutoDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GrupoProdutoDTO> findById(@PathVariable Long id){
		GrupoProdutoDTO grupoProdutoDto = service.findById(id);
		return ResponseEntity.ok(grupoProdutoDto);
	}
	
	@PostMapping
	public ResponseEntity<GrupoProdutoDTO> create(@RequestBody GrupoProdutoDTO grupoprodutoDto) {
		
		service.create(grupoprodutoDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(grupoprodutoDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GrupoProdutoDTO> update(@PathVariable Long id, @RequestBody GrupoProdutoDTO grupoprodutoDto) {
			grupoprodutoDto = service.update(id, grupoprodutoDto);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(grupoprodutoDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
}
