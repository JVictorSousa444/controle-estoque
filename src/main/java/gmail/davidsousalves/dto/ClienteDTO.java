package gmail.davidsousalves.dto;

import java.util.List;

import gmail.davidsousalves.documentos.TipoDocumento;
import gmail.davidsousalves.model.Cidade;
import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.Status;

public record ClienteDTO(Long id, String nome, String endereco, String email, List<String> telefones,
		Status status, String cpfCnpj, TipoDocumento documento, Cidade cidade, String bairro) {

	public ClienteDTO(Cliente clienteEntity) {
		this(clienteEntity.getId(), clienteEntity.getNome(), clienteEntity.getEndereco(), clienteEntity.getEmail(),
				clienteEntity.getTelefones(), clienteEntity.getStatus(), clienteEntity.getCpfCnpj(),
				clienteEntity.getDocumento(), clienteEntity.getCidade(), clienteEntity.getBairro());
	}

	public static ClienteDTO fromCliente(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getEmail(),
				cliente.getTelefones(), cliente.getStatus(), cliente.getCpfCnpj(), cliente.getDocumento(), cliente.getCidade(), cliente.getBairro());
	}
}
