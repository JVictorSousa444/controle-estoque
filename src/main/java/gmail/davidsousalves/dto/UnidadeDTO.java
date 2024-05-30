package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Unidade;

public record UnidadeDTO(Long id, String sigla) {

	public UnidadeDTO(Unidade entity) {
		this(entity.getId(), entity.getSigla());
	}
}
