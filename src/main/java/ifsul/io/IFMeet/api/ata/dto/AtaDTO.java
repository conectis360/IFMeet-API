package ifsul.io.IFMeet.api.ata.dto;


import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoDTO;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class AtaDTO {

    private Long codigoAta;

    private ReuniaoDTO reuniao;

    private String resumo;
}