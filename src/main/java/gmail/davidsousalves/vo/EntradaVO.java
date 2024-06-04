package gmail.davidsousalves.vo;

import gmail.davidsousalves.model.EntradaItem;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class EntradaVO {

    private Long id;


    @Column(nullable = false)
    private Date dataEntrada;

    @Transient
    private List<EntradaItemVO> itens;
}
