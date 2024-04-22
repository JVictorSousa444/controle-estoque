package gmail.davidsousalves.model;

public class GrupoProduto {
	
	private String descricao;
	private String nome;
	
	public GrupoProduto(String descricao, String nome) {
		this.descricao = descricao;
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
