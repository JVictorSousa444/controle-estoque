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

import gmail.davidsousalves.dto.SubGrupoProdutoDTO;
import gmail.davidsousalves.services.SubGrupoProdutoService;

@RestController
@RequestMapping("/sub-grupo-produto")
public class SubGrupoProdutoController {

	@Autowired
	private SubGrupoProdutoService service;

	@GetMapping("/busca-todos")
	public ResponseEntity<List<SubGrupoProdutoDTO>> buscarTodosClientes() {
		List<SubGrupoProdutoDTO> dto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubGrupoProdutoDTO> findById(@PathVariable Long id) {
		SubGrupoProdutoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/subgrupo-produto-paginados")
	public ResponseEntity<Page<SubGrupoProdutoDTO>> buscaPaginada(Pageable pageable) {
	    Page<SubGrupoProdutoDTO> subgrupoProdutoDTOPage = service.buscaPaginada(pageable);
	    return ResponseEntity.ok(subgrupoProdutoDTOPage);
	}

	@PostMapping
	public ResponseEntity<SubGrupoProdutoDTO> create(@RequestBody SubGrupoProdutoDTO subGrupoProduto) {
		service.create(subGrupoProduto);

		return ResponseEntity.status(HttpStatus.CREATED).body(subGrupoProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubGrupoProdutoDTO> update(@PathVariable Long id, @RequestBody SubGrupoProdutoDTO subGrupoProduto) {
		subGrupoProduto = service.update(id, subGrupoProduto);
	        return ResponseEntity.ok(subGrupoProduto);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
