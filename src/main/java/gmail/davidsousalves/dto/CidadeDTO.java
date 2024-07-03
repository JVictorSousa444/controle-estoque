package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Cidade;

public record CidadeDTO(Long id, String nome, String siglaEstado) {

	public CidadeDTO(Cidade entity) {
		this(entity.getId(), entity.getNome(), entity.getSiglaEstado());
	}
	
	public static CidadeDTO fromCidade(Cidade entity) {
		return new CidadeDTO(entity.getId(), entity.getNome(), entity.getSiglaEstado());
	}
}
