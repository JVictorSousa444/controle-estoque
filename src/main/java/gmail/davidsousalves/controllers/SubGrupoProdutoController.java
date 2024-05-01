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

import gmail.davidsousalves.model.SubgrupoProduto;
import gmail.davidsousalves.services.SubGrupoProdutoService;

@RestController
@RequestMapping("/sub-grupo-produto")
public class SubGrupoProdutoController {

	@Autowired
	private SubGrupoProdutoService service;
	
	@GetMapping
	public ResponseEntity<List<SubgrupoProduto>> getAllUnidade() {
		List<SubgrupoProduto> subGrupoProduto = service.findAll();
		return ResponseEntity.ok(subGrupoProduto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubgrupoProduto> findById(@PathVariable Long id) {
		Optional<SubgrupoProduto> subGrupoProduto = service.findById(id);
		if (subGrupoProduto.isPresent()) {
			return ResponseEntity.ok(subGrupoProduto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<SubgrupoProduto> create(@RequestBody SubgrupoProduto subGrupoProduto) {
		SubgrupoProduto savedSubGrupoProduto = service.save(subGrupoProduto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSubGrupoProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubgrupoProduto> update(@PathVariable Long id, @RequestBody SubgrupoProduto subGrupoProduto) {
		Optional<SubgrupoProduto> existingSubGrupoProduto = service.findById(id);

		if (existingSubGrupoProduto.isPresent()) {
			subGrupoProduto.setId(id);
			SubgrupoProduto updatedSubGrupoProduto = service.save(subGrupoProduto);
			return ResponseEntity.ok(updatedSubGrupoProduto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<SubgrupoProduto> subGrupoProduto = service.findById(id);
		if (subGrupoProduto.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
}
