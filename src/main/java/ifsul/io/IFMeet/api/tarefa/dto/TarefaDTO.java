package ifsul.io.IFMeet.api.tarefa.dto;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TarefaDTO {
    private Long codigoTarefa;
    private TrabalhoDTO trabalho;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private Boolean finalizada;
}

