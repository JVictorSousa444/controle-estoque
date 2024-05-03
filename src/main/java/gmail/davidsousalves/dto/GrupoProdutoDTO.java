package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.GrupoProduto;

public record GrupoProdutoDTO(String nome, String descricao) {

	public GrupoProdutoDTO(GrupoProduto entity) {
		this(entity.getNome(), entity.getDescricao());
	}
}
