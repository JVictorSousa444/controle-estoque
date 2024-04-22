package gmail.davidsousalves.model;

public class SaidaItem {

	private Saida saida;
	private Produto produto;
	private Long quantidade;
	private Double valorUnitario;
	
	
	public SaidaItem(Saida saida, Produto produto, Long quantidade, Double valorUnitario) {
		this.saida = saida;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}


	public Saida getSaida() {
		return saida;
	}


	public void setSaida(Saida saida) {
		this.saida = saida;
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
