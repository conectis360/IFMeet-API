package ifsul.io.IFMeet.api.calendarevent.mapper;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventDTO;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface CalendarEventMapper extends BeanMapper<CalendarEvent, CalendarEventDTO> {

    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "description", target = "descricao")
    @Mapping(source = "start", target = "dataInicial")
    @Mapping(source = "end", target = "dataFinal")
    @Mapping(source = "color", target = "eventoCor")
    @Mapping(source = "backgroundColor", target = "eventoCorFundo")
    @Mapping(source = "allDay", target = "diaInteiro")
    CalendarEvent toEntity(CalendarEventDTO calendarEventDTO);

    @Override
    @InheritInverseConfiguration
    CalendarEventDTO toDto(CalendarEvent calendarEvent);

}