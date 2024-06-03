package gmail.davidsousalves.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Produto {

	//@Transient
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, length = 70, unique = true)
	private String nome;
	
	@Column(nullable = false, length = 255)
	private String descricao;
	
	//@Transient
	
	@Column(nullable = false)
	private Long codigo;
	
	
	@ManyToOne
	@JoinColumn(name = "grupo_produto_id", nullable = false)
	private GrupoProduto grupoproduto;
	
	
	@ManyToOne
	@JoinColumn(name = "tipo_unidade_id", nullable = false)
	private Unidade tipoUnidade;
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;
	
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;

	private Double lucroSugerido;

	private Integer quantidade;
	

	public Produto() {
	}

	public Produto(Long id,  String nome, String descricao,  Long codigo,
			 GrupoProduto grupoproduto,  Unidade tipoUnidade,  Fabricante fabricante,
			 Double lucroSugerido,  Fornecedor fornecedor) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.codigo = codigo;
		this.grupoproduto = grupoproduto;
		this.tipoUnidade = tipoUnidade;
		this.fabricante = fabricante;
		this.lucroSugerido = lucroSugerido;
		this.fornecedor = fornecedor;
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

	public GrupoProduto getGrupoproduto() {
		return grupoproduto;
	}

	public void setGrupoproduto(GrupoProduto grupoproduto) {
		this.grupoproduto = grupoproduto;
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}