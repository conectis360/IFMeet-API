package ifsul.io.IFMeet.api.ata.dto;


import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventDTO;
import lombok.Data;

import javax.persistence.*;


@Data
public class AtaDTO {

    private Long codigoAta;
    private CalendarEventDTO evento;
    private String resumo;
    private String pauta;
}