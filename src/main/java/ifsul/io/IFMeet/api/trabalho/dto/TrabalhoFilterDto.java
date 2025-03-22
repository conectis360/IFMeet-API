package ifsul.io.IFMeet.api.trabalho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrabalhoFilterDto {

    private Long codigoOrientador;
    private Long codigoAluno;
    private Long codigoCurso;
    private Long codigoTrabalho;

}
