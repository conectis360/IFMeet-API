package ifsul.io.IFMeet.api.reuniao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ReuniaoDTO {

    private Long codigoReuniao;

    private TrabalhoDTO trabalho;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime data;

    private String pauta;

    private UsuarioDTO orientador;

    private UsuarioDTO orientando;

    private Boolean realizada;

    private Boolean aceita;

}