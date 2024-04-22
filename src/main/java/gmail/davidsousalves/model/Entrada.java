package gmail.davidsousalves.model;

import java.time.LocalDate;
import java.time.ZoneId;

public class Entrada {

	private LocalDate data;
	private Produto produto;
	private Long quantidade;
	private Double valorUnitario;
	
	public Entrada(LocalDate data, Produto produto, Long quantidade, Double valorUnitario) {
		super();
		this.data = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
}
