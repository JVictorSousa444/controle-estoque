package gmail.davidsousalves.dto;

import java.time.LocalDateTime;
import java.util.List;

import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.model.EntradaItem;

public record EntradaDTO(LocalDateTime dataEntrada, List<EntradaItem> itens) {

	public EntradaDTO(Entrada entity) {
		this(entity.getDataEntrada(), entity.getItens());
	}
}
