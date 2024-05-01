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

import gmail.davidsousalves.model.GrupoProduto;
import gmail.davidsousalves.services.GrupoProdutoService;

@RestController
@RequestMapping("/GrupoProduto")
public class GrupoProdutoController {

	@Autowired
	private GrupoProdutoService service;
	
	@GetMapping
	public ResponseEntity<List<GrupoProduto>> getAll(){
		List<GrupoProduto> grupoProduto = service.findAll();
		return ResponseEntity.ok(grupoProduto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GrupoProduto> findById(@PathVariable Long id){
		Optional<GrupoProduto> grupoProduto = service.findById(id);
		
		if(grupoProduto.isPresent()) {
			return ResponseEntity.ok(grupoProduto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<GrupoProduto> create(@RequestBody GrupoProduto grupoproduto) {
		GrupoProduto savedGrupoProduto = service.create(grupoproduto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedGrupoProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GrupoProduto> update(@PathVariable Long id, @RequestBody GrupoProduto grupoproduto) {
		Optional<GrupoProduto> existingGrupoProduto = service.findById(id);

		if (existingGrupoProduto.isPresent()) {
			grupoproduto.setId(id);
			GrupoProduto updatedGrupoProduto = service.create(grupoproduto);
			return ResponseEntity.ok(updatedGrupoProduto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<GrupoProduto> grupoProduto = service.findById(id);
		if (grupoProduto.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
