package gmail.davidsousalves.model;

public class SubgrupoProduto {

	private String descricao;
	private GrupoProduto grupoId;
	
	public SubgrupoProduto(String descricao, GrupoProduto grupoId) {
		this.descricao = descricao;
		this.grupoId = grupoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrupoProduto getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(GrupoProduto grupoId) {
		this.grupoId = grupoId;
	}
}
