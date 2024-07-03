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
	
	@NotBlank(message = "O campo nome não pode ser vazio/nulo")
	@Column(length = 70)
	private String nome;
	
	@Column(length = 255)
	private String descricao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento documento;
	
	@NotBlank(message = "O campo cpf/cnpj não pode ser vazio/nulo")
	@Column(length = 14, unique = true)
	private String numCpfCnpj;
	
	@NotBlank(message = "o campo email não pode ser vazio/nulo")
	@Email
	@Column(length = 120, unique = true)
    private String email;
	
	@NotBlank(message = "o campo telefone não pode ser vazio/nulo")
	@Column(nullable = false, length = 22)
	private String telefone;
	
	public Fabricante() {		
	}

	public Fabricante(Long id, @NotBlank(message = "O campo nome não pode ser vazio/nulo") String nome,
			String descricao, TipoDocumento documento,
			@NotBlank(message = "O campo cpf/cnpj não pode ser vazio/nulo") String numCpfCnpj,
			@NotBlank(message = "o campo email não pode ser vazio/nulo") @Email String email,
			@NotBlank(message = "o campo telefone não pode ser vazio/nulo") String telefone) {
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numCpfCnpj == null) ? 0 : numCpfCnpj.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (documento != other.documento)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numCpfCnpj == null) {
			if (other.numCpfCnpj != null)
				return false;
		} else if (!numCpfCnpj.equals(other.numCpfCnpj))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
}
