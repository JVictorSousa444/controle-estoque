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

import gmail.davidsousalves.dto.ProdutoDTO;
import gmail.davidsousalves.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<ProdutoDTO>> buscarTodos() {
		List<ProdutoDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
		ProdutoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> buscaPaginada(String nome, Pageable pageable) {
	    Page<ProdutoDTO> produtoDTOPage = service.buscaPaginada(nome, pageable);
	    return ResponseEntity.ok(produtoDTOPage);
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDto) {
		
		ProdutoDTO createProduto =  service.create(produtoDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(createProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
		produto = service.update(id, produto);
        return ResponseEntity.ok(produto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}