package gmail.davidsousalves.dto;

import java.time.LocalDateTime;

import gmail.davidsousalves.model.Fornecedor;
import gmail.davidsousalves.model.Status;

public record FornecedorDTO(Long id, String nome, String descricao, String email, String telefone, String cnpj, 
		String bairro, String cep, LocalDateTime dataCadastro, Status status) {

	public FornecedorDTO(Fornecedor entity) {
		this(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getEmail(), entity.getTelefone(), entity.getCnpj(),
				entity.getBairro(), entity.getCep(), entity.getDataCadastro(), entity.getStatus());
	}
}
