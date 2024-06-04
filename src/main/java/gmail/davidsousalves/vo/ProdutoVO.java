package gmail.davidsousalves.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoVO {

    private Long id;
    private String nome;
    private Integer quantidade;
}
