package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Trabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Usuario aluno;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Usuario orientador;

    @Column(name = "dataInicio")
    private LocalDateTime dataInicio;

    @Column(name = "dataFim")
    private LocalDateTime dataFim;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(name = "problema")
    private String problema;

    @Column(name = "justificativa")
    private String justificativa;

    @Column(name = "hipotese")
    private String hipotese;

    @Column(name = "solucao")
    private String solucao;
}
