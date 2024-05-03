package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Estoque;
import gmail.davidsousalves.model.Produto;

public record EstoqueDTO(Produto produto, Long quantidade) {

	public EstoqueDTO(Estoque entity) {
		this(entity.getProduto(), entity.getQuantidade());
	}
}
