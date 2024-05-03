package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Fornecedor;

public record FornecedorDTO(String nome, String descricao, String email, String telefone ) {

	public FornecedorDTO(Fornecedor entity) {
		this(entity.getNome(), entity.getDescricao(), entity.getEmail(), entity.getTelefone());
	}
}
