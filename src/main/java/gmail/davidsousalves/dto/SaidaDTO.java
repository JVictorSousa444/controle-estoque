package gmail.davidsousalves.dto;

import java.time.LocalDateTime;
import java.util.List;

import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.model.SaidaItem;

public record SaidaDTO(Long id, LocalDateTime data, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente, List<SaidaItem> itens) {

	public SaidaDTO(Saida entity) {
		this(entity.getId(), entity.getData(), entity.getDataVencimento(),entity.getDataPagamento(), entity.getCliente(), entity.getItens());
	}
}
