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

import gmail.davidsousalves.dto.GrupoProdutoDTO;
import gmail.davidsousalves.services.GrupoProdutoService;

@RestController
@RequestMapping("/api/GrupoProduto")
public class GrupoProdutoController {

	@Autowired
	private GrupoProdutoService service;
	
	@GetMapping("/busca-todos")
	public ResponseEntity<List<GrupoProdutoDTO>> buscarTodos() {
		List<GrupoProdutoDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GrupoProdutoDTO> findById(@PathVariable Long id){
		GrupoProdutoDTO grupoProdutoDto = service.findById(id);
		return ResponseEntity.ok(grupoProdutoDto);
	}
	
	@GetMapping()
	public ResponseEntity<Page<GrupoProdutoDTO>> buscaPaginada(Pageable pageable) {
	    Page<GrupoProdutoDTO> grupoProdutoDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(grupoProdutoDTOPage);
	}
	
	@PostMapping
	public ResponseEntity<GrupoProdutoDTO> create(@RequestBody GrupoProdutoDTO grupoprodutoDto) {
		
		GrupoProdutoDTO createGrupoProduto = service.create(grupoprodutoDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(createGrupoProduto);
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
