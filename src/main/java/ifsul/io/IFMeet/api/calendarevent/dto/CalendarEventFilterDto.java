package ifsul.io.IFMeet.api.calendarevent.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class CalendarEventFilterDto {

    Long codigoUsuario;
    Long codigoTrabalho;
    Long codigoOrientador;
}