package ifsul.io.IFMeet.domain.calendarevent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Table(schema = "public", name = "calendario_event")
@DynamicUpdate
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_inicial")
    private LocalDateTime dataInicial;

    @Column(name = "data_final")
    private LocalDateTime dataFinal;

    @Column(name = "evento_cor")
    private String eventoCor;

    @Column(name = "evento_cor_fundo")
    private String eventoCorFundo;

    @Column(name = "dia_inteiro")
    private Boolean diaInteiro;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalho_id")
    private Trabalho trabalho;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;


}