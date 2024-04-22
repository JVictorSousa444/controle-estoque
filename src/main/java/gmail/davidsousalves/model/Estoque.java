package gmail.davidsousalves.model;

import java.util.List;

public class Estoque {

	private List<Produto> produto;
	private Long quantidade;
	
	public Estoque(List<Produto> produto, Long quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
}
