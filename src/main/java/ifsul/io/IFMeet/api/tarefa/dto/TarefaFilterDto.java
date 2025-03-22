package ifsul.io.IFMeet.api.tarefa.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TarefaFilterDto {

    private Long codigoOrientador;
    private Long codigoAluno;
    private Long codigoCurso;
    private Long codigoTrabalho;

}
