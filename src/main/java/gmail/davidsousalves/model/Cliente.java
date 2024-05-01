package gmail.davidsousalves.model;

import gmail.davidsousalves.documentos.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name =  "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false ,length = 70)
	private String nome;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento documento;
	
	@NotBlank
	@Column(nullable = false, length = 25, unique = true)
	private String cpfCnpj;
	
	@NotBlank
	@Column(nullable = false, length = 120)
	private String endereco;
	
	@NotBlank
    @Email
	@Column(nullable = false, length = 120, unique = true)
    private String email;
	
	@NotBlank
	@Column(nullable = false, length = 22)
	private String telefone;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	
	
	public Cliente() {
	}
	

	


	public Cliente(Long id, @NotBlank String nome, @NotBlank TipoDocumento documento, @NotBlank String cpfCnpj,
			@NotBlank String endereco, @NotBlank @Email String email, @NotBlank String telefone,
			@NotBlank StatusCliente status) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.status = status;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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





	public String getCpfCnpj() {
		return cpfCnpj;
	}





	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}





	public String getEndereco() {
		return endereco;
	}





	public void setEndereco(String endereco) {
		this.endereco = endereco;
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





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
