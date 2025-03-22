package ifsul.io.IFMeet.domain.reuniao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Table(schema = "public", name = "reuniao")
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

    @Column(name = "realizada")
    private Boolean realizada;

    @Column(name = "aceita")
    private Boolean aceita;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proponente_id")
    private Usuario proponente;
}
