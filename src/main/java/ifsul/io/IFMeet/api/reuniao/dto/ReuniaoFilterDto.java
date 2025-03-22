package ifsul.io.IFMeet.api.reuniao.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReuniaoFilterDto {

    private Long codigoOrientador;
    private Long codigoAluno;
    private Long codigoCurso;
    private Long codigoTrabalho;

}
