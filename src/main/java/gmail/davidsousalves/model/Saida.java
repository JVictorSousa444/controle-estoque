package gmail.davidsousalves.model;

import java.time.LocalDate;
import java.time.ZoneId;

public class Saida {

	private LocalDate data;
	private LocalDate dataVencimento;
	private Cliente cliente;
	
	public Saida(LocalDate data, LocalDate dataVencimento, Cliente cliente) {
		this.data = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
		this.dataVencimento = dataVencimento;
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
