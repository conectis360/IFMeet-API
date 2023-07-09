package ifsul.io.IFMeet.controller.dto;

import ifsul.io.IFMeet.domain.Trabalho;
import ifsul.io.IFMeet.domain.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ReuniaoDTO {

    private Long codigoReuniao;

    private Trabalho trabalho;

    private LocalDateTime data;

    private Timestamp horaInicio;

    private Timestamp horaFim;

    private String pauta;

    private Usuario proponente;
}