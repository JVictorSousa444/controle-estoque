package gmail.davidsousalves.model;

public class Produto {

	private Long id;
	private String descricao;
	private Long codigo;
	private GrupoProduto grupoproduto;
	private Unidade tipoUnidade;
	private Fabricante fabricante;
	private Double lucroSugerido;
	
	private Fornecedor fornecedorId;

	public Produto(Long id, String descricao, Long codigo, GrupoProduto grupoproduto, Unidade tipoUnidade,
			Fabricante fabricante, Double lucroSugerido, Fornecedor fornecedorId) {
		this.id = id;
		this.descricao = descricao;
		this.codigo = codigo;
		this.grupoproduto = grupoproduto;
		this.tipoUnidade = tipoUnidade;
		this.fabricante = fabricante;
		this.lucroSugerido = lucroSugerido;
		this.fornecedorId = fornecedorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Fornecedor getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Fornecedor fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	
}
