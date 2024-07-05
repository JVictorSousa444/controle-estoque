package gmail.davidsousalves.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entrada implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "A data de entrada não pode ser vazia")
    private LocalDateTime dataEntrada;

	@Transient
	private List<EntradaItem> itens;

	@Column
	private LocalDateTime dataPagamento;

	@Column
	private LocalDateTime dataVencimento;

	@NotNull(message = "O campo fornecedor não pode ser nulo/vazia")
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;
	
	public Entrada() {
	}

	public Entrada(Long id, LocalDateTime dataEntrada) {
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

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public @NotNull(message = "O campo fornecedor não pode ser nulo/vazia") Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(@NotNull(message = "O campo fornecedor não pode ser nulo/vazia") Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
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

}
