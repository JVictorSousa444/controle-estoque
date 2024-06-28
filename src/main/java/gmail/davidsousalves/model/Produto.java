package gmail.davidsousalves.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio/nulo")
	@Column(length = 70, unique = true)
	private String nome;
	
	@Column(nullable = false, length = 255)
	private String descricao;
		
	@Column(nullable = false)
	private Long codigo;
	
	@NotNull(message = "O campo grupo produto não pode ser nulo/vazia")
	@ManyToOne
	@JoinColumn(name = "grupo_produto_id")
	private GrupoProduto grupoProduto;
	
	@NotNull(message = "O campo unidade não pode ser nulo/vazia")
	@ManyToOne
	@JoinColumn(name = "tipo_unidade_id")
	private Unidade tipoUnidade;
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;
	
	
	@NotNull(message = "O lucro sugerido não pode ser nula/vazio")
	@DecimalMin(value = "0.01", message = "O lucro seguerido deve ser maior que zero")
	private Double lucroSugerido;

	@NotNull(message = "A quantidade não pode ser nula")
	private Integer quantidade;
	

	public Produto() {
	}


	public Produto(Long id, @NotBlank(message = "O campo nome não pode ser vazio/nulo") String nome, String descricao,
			Long codigo, @NotNull(message = "O campo grupo produto não pode ser nulo/vazia") GrupoProduto grupoProduto,
			@NotNull(message = "O campo unidade não pode ser nulo/vazia") Unidade tipoUnidade, Fabricante fabricante,
			@NotNull(message = "O lucro sugerido não pode ser nula/vazio") @DecimalMin(value = "0.01", message = "O lucro seguerido deve ser maior que zero") Double lucroSugerido,
			@NotNull(message = "A quantidade não pode ser nula") Integer quantidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.codigo = codigo;
		this.grupoProduto = grupoProduto;
		this.tipoUnidade = tipoUnidade;
		this.fabricante = fabricante;
		this.lucroSugerido = lucroSugerido;
		this.quantidade = quantidade;
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


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}


	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}


	public Unidade getTipoUnidade() {
		return tipoUnidade;
	}


	public void setTipoUnidade(Unidade tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}


	public Fabricante getFabricante() {
		return fabricante;
	}


	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}


	public Double getLucroSugerido() {
		return lucroSugerido;
	}


	public void setLucroSugerido(Double lucroSugerido) {
		this.lucroSugerido = lucroSugerido;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((grupoProduto == null) ? 0 : grupoProduto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lucroSugerido == null) ? 0 : lucroSugerido.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((tipoUnidade == null) ? 0 : tipoUnidade.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (grupoProduto == null) {
			if (other.grupoProduto != null)
				return false;
		} else if (!grupoProduto.equals(other.grupoProduto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lucroSugerido == null) {
			if (other.lucroSugerido != null)
				return false;
		} else if (!lucroSugerido.equals(other.lucroSugerido))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (tipoUnidade == null) {
			if (other.tipoUnidade != null)
				return false;
		} else if (!tipoUnidade.equals(other.tipoUnidade))
			return false;
		return true;
	}
	
}