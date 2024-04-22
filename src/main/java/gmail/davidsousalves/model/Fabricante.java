package gmail.davidsousalves.model;

import gmail.davidsousalves.documentos.TipoDocumento;

public class Fabricante {

	private String descricao;
	private String nome;
	private TipoDocumento documento;
	
	public Fabricante(String descricao, String nome, TipoDocumento documento) {
		this.descricao = descricao;
		this.nome = nome;
		this.documento = documento;
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

	public TipoDocumento getDocumento() {
		return documento;
	}

	public void setDocumento(TipoDocumento documento) {
		this.documento = documento;
	}
	

}
