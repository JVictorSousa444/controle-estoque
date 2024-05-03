package gmail.davidsousalves.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import gmail.davidsousalves.documentos.TipoDocumento;
import gmail.davidsousalves.dto.ClienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name =  "cliente")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false ,length = 70)
	private String nome;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento documento;
	
	
	@Column(nullable = false, length = 25, unique = true)
	private String cpfCnpj;
	
	
	@Column(nullable = false, length = 120)
	private String endereco;
	
	
    @Email
	@Column(nullable = false, length = 100, unique = true)
    private String email;
	
	
	@Column(nullable = false, length = 20)
	private String telefone;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	
	
	public Cliente() {
	}

	public Cliente(ClienteDTO dto) {
		this.cpfCnpj = dto.cpfCnpj();
		this.documento = dto.documento();
		this.email = dto.email();
		this.endereco = dto.endereco();
		this.status = dto.status();
		this.telefone = dto.telefone();
	}
	

	public Cliente(Long id,  String nome,  TipoDocumento documento,  String cpfCnpj,
			 String endereco,   String email,  String telefone,
			 StatusCliente status) {
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
