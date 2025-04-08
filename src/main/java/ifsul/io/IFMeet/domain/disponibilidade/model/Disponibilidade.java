package ifsul.io.IFMeet.domain.disponibilidade.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Table(schema = "public", name = "disponibilidade")
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "dia_semana")
    private Integer diaSemana;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    private LocalTime horaFim;
}
