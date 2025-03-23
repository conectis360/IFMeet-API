package ifsul.io.IFMeet.domain.notificacao.model;

import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(schema = "public", name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "data_baixa")
    private LocalDateTime dataBaixa;

    @Column(name = "conteudo")
    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_notificacao_id", referencedColumnName = "id")
    private TipoNotificacao tipoNotificacao;
}