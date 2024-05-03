package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.ClienteDTO;
import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	public ClienteDTO findById(Long id){
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new ClienteDTO(cliente);
	}

	public ClienteDTO create(ClienteDTO clienteDto) {
		Cliente entity = new Cliente();
		copyDtoToEntity(clienteDto, entity);
		entity = clienteRepository.save(entity);
		return new ClienteDTO(entity);
	}

	public ClienteDTO update(Long id, ClienteDTO dto) {

		Cliente entity = clienteRepository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = clienteRepository.save(entity);
		return new ClienteDTO(entity);
	}

	public void deleteById(Long id) {
		if (!clienteRepository.existsById(id)) {
    		throw new IllegalArgumentException("Recurso não encontrado");
    	}
    	try {
    		clienteRepository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
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

	}
}
