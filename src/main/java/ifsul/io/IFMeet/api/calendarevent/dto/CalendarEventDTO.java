package ifsul.io.IFMeet.api.calendarevent.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class CalendarEventDTO {

    private Long codigoEvento;

    private String titulo;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataInicial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataFinal;

    private String eventoCor;
}