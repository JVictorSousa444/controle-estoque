package gmail.davidsousalves.dto;

import gmail.davidsousalves.documentos.TipoDocumento;
import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.StatusCliente;

public record ClienteDTO(String nome, String endereco, String email, 
		String telefone, StatusCliente status, String cpfCnpj, TipoDocumento documento) {

	public ClienteDTO(Cliente clienteEntity) {
        this(clienteEntity.getNome(), clienteEntity.getEndereco(),
             clienteEntity.getEmail(), clienteEntity.getTelefone(), clienteEntity.getStatus(),
             clienteEntity.getCpfCnpj(), clienteEntity.getDocumento());
    }

}
