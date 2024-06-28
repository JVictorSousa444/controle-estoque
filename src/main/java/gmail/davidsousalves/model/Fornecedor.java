package gmail.davidsousalves.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio/nulo")
	@Column(length = 70)
	private String nome;
	
	@Column(length = 255)
	private String descricao;
	
	@NotBlank(message = "O campo telefone não pode ser vazio/nulo")
	@Column(length = 15)
	private String telefone;
	
	@NotBlank(message = "O campo email não pode ser vazio/nulo")
    @Email
	@Column(nullable = false, length = 120)
	private String email;
	
	@NotBlank(message = "O campo CNPJ não pode ser vazio/nulo")
	@Column(length = 25)
	private String cnpj;
	
	@NotBlank(message = "O campo bairro não pode ser vazio/nulo")
	private String bairro;
	
	@NotBlank(message = "O campo cep não pode ser vazio/nulo")
	private String cep;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "A data de cadastro não pode ser vazia")
	private LocalDateTime dataCadastro;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Fornecedor() {
	}

	public Fornecedor(Long id, @NotBlank(message = "O campo nome não pode ser vazio/nulo") String nome,
			String descricao, @NotBlank(message = "O campo telefone não pode ser vazio/nulo") String telefone,
			@NotBlank(message = "O campo email não pode ser vazio/nulo") @Email String email,
			@NotBlank(message = "O campo CNPJ não pode ser vazio/nulo") String cnpj,
			@NotBlank(message = "O campo bairro não pode ser vazio/nulo") String bairro,
			@NotBlank(message = "O campo cep não pode ser vazio/nulo") String cep,
			@NotNull(message = "A data de entrada não pode ser vazia") LocalDateTime dataCadastro, Status status) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.telefone = telefone;
		this.email = email;
		this.cnpj = cnpj;
		this.bairro = bairro;
		this.cep = cep;
		this.dataCadastro = dataCadastro;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (status != other.status)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
