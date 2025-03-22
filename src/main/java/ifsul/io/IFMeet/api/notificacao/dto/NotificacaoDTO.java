package ifsul.io.IFMeet.api.notificacao.dto;


import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class NotificacaoDTO {

    private Long codigoNotificacao;

    private LocalDateTime data;

    private LocalDateTime dataBaixa;

    private String conteudo;

    private UsuarioDTO usuario;
}