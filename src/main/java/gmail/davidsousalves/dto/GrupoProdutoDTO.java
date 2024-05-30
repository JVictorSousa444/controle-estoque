package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.GrupoProduto;

public record GrupoProdutoDTO(Long id, String nome, String descricao) {

	public GrupoProdutoDTO(GrupoProduto entity) {
		this(entity.getId(), entity.getNome(), entity.getDescricao());
	}
}
