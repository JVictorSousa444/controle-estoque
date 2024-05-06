package gmail.davidsousalves.dto;

import java.time.LocalDateTime;

import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.Saida;

public record SaidaDTO(LocalDateTime data, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {

	public SaidaDTO(Saida entity) {
		this(entity.getData(), entity.getDataVencimento(),entity.getDataPagamento(), entity.getCliente());
	}
}
