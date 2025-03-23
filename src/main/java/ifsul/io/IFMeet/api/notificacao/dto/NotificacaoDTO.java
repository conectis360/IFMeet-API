package ifsul.io.IFMeet.api.notificacao.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataBaixa;

    private String conteudo;

    private UsuarioDTO usuario;
}