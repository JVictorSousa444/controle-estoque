package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import gmail.davidsousalves.documentos.TipoDocumento;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.ClienteDTO;
import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.StatusCliente;
import gmail.davidsousalves.repositories.ClienteRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;


	public List<ClienteDTO> buscaClienteNomeStatus(String nome, StatusCliente status) {

		List<Cliente> clientes = clienteRepository.findByNomeAndStatus(nome, status);

		return clientes.stream().map(cliente -> copyEntitytoDto(cliente)).collect(Collectors.toList());

	}

	public List<ClienteDTO> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes.stream().map(cliente -> copyEntitytoDto(cliente)).collect(Collectors.toList());
	}

	public Page<ClienteDTO> buscaPaginada(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(ClienteDTO::fromCliente);
    }



	public ClienteDTO findById(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id: " + id + " nao existe"));
		
		return new ClienteDTO(cliente);	
		
	}

	public ClienteDTO create(ClienteDTO clienteDto) {
		Cliente entity = new Cliente();
		copyDtoToEntity(clienteDto, entity);
		entity = clienteRepository.save(entity);
		return new ClienteDTO(entity);
	}

	public ClienteDTO update(Long id, ClienteDTO dto) {
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = clienteRepository.save(entity);
			return new ClienteDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");

		}

	}

	public void deleteById(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
		entity.setNome(dto.nome());
		entity.setCpfCnpj(dto.cpfCnpj());
		entity.setDocumento(dto.documento());
		entity.setEmail(dto.email());
		entity.setEndereco(dto.endereco());
		entity.setStatus(dto.status());
		entity.setTelefone(dto.telefone());
		entity.setDocumento(buscarTipoDocumento(dto));

	}

	private ClienteDTO copyEntitytoDto(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO(cliente);
		return dto;
	}

	private TipoDocumento buscarTipoDocumento(ClienteDTO clienteDTO) {
		if (clienteDTO == null || StringUtils.isEmpty(clienteDTO.cpfCnpj())) {
			return null;
		}

		return clienteDTO.cpfCnpj().length() > 11 ? TipoDocumento.CNPJ : TipoDocumento.CPF;
	}
}
