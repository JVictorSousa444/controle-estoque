package gmail.davidsousalves.vo;

import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.SaidaItem;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaidaVO {

    private Long id;

    private LocalDateTime data;

    private LocalDateTime dataVencimento;

    private LocalDateTime dataPagamento;

    private ClienteVO cliente;

    @Transient
    private List<SaidaItemVO> itens;
}
