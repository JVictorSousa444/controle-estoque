package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.model.Produto;

public record EntradaItemDTO(Entrada entrada, Produto produto, Long quantidade, Double valorUnitario) {

	public EntradaItemDTO(EntradaItem entity) {
		this(entity.getEntrada(), entity.getProduto(), entity.getQuantidade(), entity.getValorUnitario());
	}
}
