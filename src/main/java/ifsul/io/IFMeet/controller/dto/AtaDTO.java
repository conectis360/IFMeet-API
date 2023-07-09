package ifsul.io.IFMeet.controller.dto;


import ifsul.io.IFMeet.domain.Reuniao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class AtaDTO {

    private Long codigoAta;

    private Reuniao reuniao;

    private String resumo;
}