package ifsul.io.IFMeet.api.documentacao.dto;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DocumentacaoDTO {

    private Long codigoDocumentacao;

    private Reuniao reuniao;
    private TrabalhoDTO trabalho;
    private String descricao;
}