package ifsul.io.IFMeet.controller.dto;

import ifsul.io.IFMeet.domain.Status;
import ifsul.io.IFMeet.domain.Trabalho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class TarefaDTO {

    private Long codigoTarefa;

    private Trabalho trabalho;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Status status;

    private String descricao;
}

