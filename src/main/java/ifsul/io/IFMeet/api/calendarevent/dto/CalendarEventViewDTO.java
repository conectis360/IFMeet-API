package ifsul.io.IFMeet.api.calendarevent.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class CalendarEventViewDTO {
    private Long id;

    private String titulo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicial;

    private String nomeAluno;

    private String trabalhoTitulo;
}