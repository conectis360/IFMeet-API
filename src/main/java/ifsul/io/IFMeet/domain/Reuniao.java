package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalho_id")
    private Trabalho trabalho;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "hora_inicio")
    private Timestamp horaInicio;

    @Column(name = "hora_fim")
    private Timestamp horaFim;

    @Column(name = "pauta")
    private String pauta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orientando_id")
    private Usuario orientando;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orientador_id")
    private Usuario orientador;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proponente_id")
    private Usuario proponente;
}
