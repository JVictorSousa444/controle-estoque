package gmail.davidsousalves.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import gmail.davidsousalves.documentos.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fabricante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, length = 70)
	private String nome;
	
	@Column(length = 255)
	private String descricao;
	
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento documento;
	
	@NotBlank
	@Column(nullable = false, length = 14, unique = true)
	private String numCpfCnpj;
	
	@Email
	@Column(nullable = false, length = 120, unique = true)
    private String email;
	
	
	@Column(nullable = false, length = 22)
	private String telefone;
	
	public Fabricante() {		
	}

	
	public Fabricante(Long id,  String nome, String descricao, TipoDocumento documento,
			 String numCpfCnpj,  String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.documento = documento;
		this.numCpfCnpj = numCpfCnpj;
		this.email = email;
		this.telefone = telefone;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public TipoDocumento getDocumento() {
		return documento;
	}


	public void setDocumento(TipoDocumento documento) {
		this.documento = documento;
	}


	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}


	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
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
		Fabricante other = (Fabricante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
