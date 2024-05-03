package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Produto;
import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.model.SaidaItem;

public record SaidaItemDTO(Saida saida, Produto produto, Long quantidade, Double valorUnitario) {

	public SaidaItemDTO(SaidaItem entity) {
		this(entity.getSaida(), entity.getProduto(), entity.getQuantidade(), entity.getValorUnitario());
	}
}
