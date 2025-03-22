package ifsul.io.IFMeet.api.tcc.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TCCDTO {

    private Long codigoTCC;

    //private TrabalhoDTO trabalho;

    private String versao;

    private String comentario;
}

