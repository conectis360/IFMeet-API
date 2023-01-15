package ifsul.io.IFMeet.controller.dto;

import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.domain.Trabalho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DocumentacaoDTO {

    private Long id;

    private Reuniao reuniao;
    private Trabalho trabalho;
    private String descricao;
}