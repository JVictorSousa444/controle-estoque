package gmail.davidsousalves.model;

public class Fornecedor {

	private String descricao;
	private String telefone;
	private String email;
	
	public Fornecedor(String desc, String telefone, String email) {
		this.descricao = desc;
		this.telefone = telefone;
		this.email = email;
	}
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
