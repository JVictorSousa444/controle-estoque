package gmail.davidsousalves.vo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntradaVO {

    private Long id;

    @Column(nullable = false)
    private Date dataEntrada;

    @Transient
    private List<EntradaItemVO> itens;
}
