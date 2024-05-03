package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.GrupoProduto;
import gmail.davidsousalves.model.SubgrupoProduto;

public record SubGrupoProdutoDTO(String descricao, GrupoProduto grupoProduto){

	public SubGrupoProdutoDTO(SubgrupoProduto entity) {
		this(entity.getDescricao(), entity.getGrupoProduto());
	}
	
}
