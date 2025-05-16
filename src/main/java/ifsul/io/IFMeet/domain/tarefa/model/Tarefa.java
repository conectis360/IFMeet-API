package ifsul.io.IFMeet.domain.tarefa.model;

import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalho_id")
    private Trabalho trabalho;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "finalizada")
    private Boolean finalizada;

    @Column(name = "descricao")
    private String descricao;

}
