package ifsul.io.IFMeet.api.calendarevent.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import ifsul.io.IFMeet.api.status.dto.StatusDTO;
import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class CalendarEventDTO {
    private Long id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end;
    private String description;
    private String color;
    private String backgroundColor;
    private Boolean allDay;
    private TrabalhoDTO trabalho;
    private StatusDTO status;
}