package ifsul.io.IFMeet.api.notificacao.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class NotificacaoViewDTO {
    private String nomeUsuario;
    private byte[] fotoUsuario;
    private String conteudoNotificacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'HH:mm:ss")
    private LocalDateTime dataNotificacao;
    private String tipoNotificacao;
    private Boolean visualizada;
}