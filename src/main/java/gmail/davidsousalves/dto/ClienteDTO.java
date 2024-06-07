package gmail.davidsousalves.dto;

import gmail.davidsousalves.documentos.TipoDocumento;
import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.StatusCliente;

public record ClienteDTO(Long id, String nome, String endereco, String email,
		String telefone, StatusCliente status, String cpfCnpj, TipoDocumento documento) {

	public ClienteDTO(Cliente clienteEntity) {
        this(clienteEntity.getId(), clienteEntity.getNome(), clienteEntity.getEndereco(),
             clienteEntity.getEmail(), clienteEntity.getTelefone(), clienteEntity.getStatus(),
             clienteEntity.getCpfCnpj(), clienteEntity.getDocumento());
    }
	
	 public static ClienteDTO fromCliente(Cliente cliente) {
	        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getEmail(),
	                cliente.getTelefone(), cliente.getStatus(), cliente.getCpfCnpj(), cliente.getDocumento());
	    }
	
}

