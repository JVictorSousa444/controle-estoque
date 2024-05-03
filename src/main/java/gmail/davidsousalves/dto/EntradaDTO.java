package gmail.davidsousalves.dto;

import java.time.LocalDateTime;

import gmail.davidsousalves.model.Entrada;

public record EntradaDTO(LocalDateTime dataEntrada) {

	public EntradaDTO(Entrada entity) {
		this(entity.getDataEntrada());
	}
}
