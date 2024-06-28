package gmail.davidsousalves.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import gmail.davidsousalves.documentos.TipoDocumento;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo nome não pode ser vazio/nulo")
	@Column(length = 70)
	private String nome;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento documento;

	@NotBlank(message = "o campo cpf/cnpj não pode ser vazio/nulo")
	@Column(length = 25, unique = true)
	private String cpfCnpj;

	@NotBlank(message = "o campo endereco não pode ser vazio/nulo")
	@Column(length = 120)
	private String endereco;

	@NotBlank(message = "o campo email não pode ser vazio/nulo")
	@Email
	@Column(length = 100, unique = true)
	private String email;

	@ElementCollection
	@CollectionTable(name = "cliente_telefones", joinColumns = @JoinColumn(name = "cliente_id"))
	@Column(name = "telefone", length = 20)
	private List<String> telefones = new ArrayList<>();

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
	
	@NotBlank(message = "O campo bairro não pode ser vazio/nulo")
	@Column(length = 100)
	private String bairro;

	public Cliente() {
	}

	public Cliente(Long id, @NotBlank(message = "O campo nome não pode ser vazio/nulo") String nome,
			TipoDocumento documento, @NotBlank(message = "o campo cpf/cnpj não pode ser vazio/nulo") String cpfCnpj,
			@NotBlank(message = "o campo endereco não pode ser vazio/nulo") String endereco,
			@NotBlank(message = "o campo email não pode ser vazio/nulo") @Email String email, List<String> telefones,
			Status status, Cidade cidade,
			@NotBlank(message = "O campo bairro não pode ser vazio/nulo") String bairro) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.email = email;
		this.telefones = telefones;
		this.status = status;
		this.cidade = cidade;
		this.bairro = bairro;
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

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telefones == null) ? 0 : telefones.hashCode());
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
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (documento != other.documento)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
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
		if (status != other.status)
			return false;
		if (telefones == null) {
			if (other.telefones != null)
				return false;
		} else if (!telefones.equals(other.telefones))
			return false;
		return true;
	}

}
