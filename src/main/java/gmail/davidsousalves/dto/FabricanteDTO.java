package gmail.davidsousalves.dto;

import gmail.davidsousalves.documentos.TipoDocumento;
import gmail.davidsousalves.model.Fabricante;

public record FabricanteDTO(Long id, String nome, String descricao, TipoDocumento documento,
		String numCpfCnpj, String telefone, String email) {

	public FabricanteDTO(Fabricante entity) {
		this(entity.getId(),entity.getNome(), entity.getDescricao(), entity.getDocumento(), entity.getNumCpfCnpj(),
				entity.getTelefone(), entity.getEmail());
	}
}
