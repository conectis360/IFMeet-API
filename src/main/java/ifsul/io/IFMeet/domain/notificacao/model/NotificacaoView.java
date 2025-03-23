package ifsul.io.IFMeet.domain.notificacao.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(schema = "public", name = "view_notificacoes_usuario")
public class NotificacaoView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "foto_usuario")
    private byte[] fotoUsuario;

    @Column(name = "conteudo_notificacao")
    private String conteudoNotificacao;

    @Column(name = "data_notificacao")
    private LocalDateTime dataNotificacao;

    @Column(name = "tipo_notificacao")
    private String tipoNotificacao;

    @Column(name = "visualizada")
    private Boolean visualizada;
}