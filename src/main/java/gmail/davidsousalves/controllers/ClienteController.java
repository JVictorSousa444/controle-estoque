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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gmail.davidsousalves.dto.ClienteDTO;
import gmail.davidsousalves.model.Status;
import gmail.davidsousalves.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/busca-todos")
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<ClienteDTO> clientesDTO = clienteService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(clientesDTO);
	}

	@GetMapping("/busca")
	public ResponseEntity<List<ClienteDTO>> buscaClienteNomeStatus(
            @RequestParam String nome,
            @RequestParam(required = false) Status status) {
        List<ClienteDTO> clientes = clienteService.buscaClienteNomeStatus(nome, status);
        return ResponseEntity.ok(clientes);
    }

	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> buscaPaginada(String nome, Pageable pageable) {
	    Page<ClienteDTO> clienteDTOPage = clienteService.buscaPaginada(nome, pageable);
	    return ResponseEntity.ok(clienteDTOPage);
	}


	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
		ClienteDTO dto = clienteService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {

		ClienteDTO createdCliente = clienteService.create(clienteDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
		dto = clienteService.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
