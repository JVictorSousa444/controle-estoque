package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Unidade;

public record UnidadeDTO(String sigla) {

	public UnidadeDTO(Unidade entity) {
		this(entity.getSigla());
	}
}
