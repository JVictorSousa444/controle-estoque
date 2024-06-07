package gmail.davidsousalves.model;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false)
	private LocalDateTime dataEntrada;

	@Transient
	private List<EntradaItem> itens;
	
	
	public Entrada() {
	}
	

	public Entrada(Long id, LocalDateTime dataEntrada) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setItens(List<EntradaItem> itens) {
		this.itens = itens;
	}

	public List<EntradaItem> getItens() {
		return itens;
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
		Entrada other = (Entrada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Fornecedor getFornecedor() {
	
		return new Fornecedor();
	}


	public Produto getProduto() {
		return new Produto();
	}
	
}
