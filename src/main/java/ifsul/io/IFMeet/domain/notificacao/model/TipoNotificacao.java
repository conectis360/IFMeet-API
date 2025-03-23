package ifsul.io.IFMeet.domain.notificacao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(schema = "public", name = "tipo_notificacao")
public class TipoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_notificacao")
    private String tipoNotificacao;

    @Column(name = "icon")
    private String icone;
}
