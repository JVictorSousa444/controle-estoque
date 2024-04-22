package gmail.davidsousalves.model;

import gmail.davidsousalves.documentos.TipoDocumento;

public class Cliente {

	private String nome;
	private TipoDocumento documento;
	private String email;
	private String telefone;
	private StatusCliente status;
	
	
	public Cliente(String nome, TipoDocumento documento, String email, String telefone, StatusCliente status) {
		this.nome = nome;
		this.documento = documento;
		this.email = email;
		this.telefone = telefone;
		this.status = status;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public StatusCliente getStatus() {
		return status;
	}


	public void setStatus(StatusCliente status) {
		this.status = status;
	}
	
}
