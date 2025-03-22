package ifsul.io.IFMeet.api.tarefa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class TarefaDTO {

    private Long codigoTarefa;

    private TrabalhoDTO trabalho;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataFim;

    private Status status;

    private String descricao;
    private Boolean finalizada;
}

