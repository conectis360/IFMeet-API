package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "dataInicio")
    private LocalDateTime dataInicio;

    @Column(name = "dataFim")
    private LocalDateTime dataFim;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "descricao")
    private String descricao;

}
