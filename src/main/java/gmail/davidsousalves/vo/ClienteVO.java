package gmail.davidsousalves.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteVO {

    private Long id;
    private String nome;
}
