package gmail.davidsousalves.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaidaItem {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo saida não pode ser nulo/vazia")
	@JoinColumn(name = "saida_id")
	@ManyToOne
	private Saida saida;
	
	@NotNull(message = "O campo produto não pode ser nulo/vazia")
	@JoinColumn(nullable = false, name = "produto_id")
	@ManyToOne
	private Produto produto;
	
	@NotNull(message = "A quantidade não pode ser nula/vazia")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
	private Long quantidade;

	@NotNull(message = "O valor unitário não pode ser nulo/vazia")
    @DecimalMin(value = "0.01", message = "O valor unitário deve ser maior que zero")
	private Double valorUnitario;
	
	
	public SaidaItem() {
	}

	public SaidaItem(Long id, Saida saida, Produto produto, Long quantidade, Double valorUnitario) {
		this.id = id;
		this.saida = saida;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
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
		SaidaItem other = (SaidaItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
