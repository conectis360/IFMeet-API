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
    @InheritInverseConfiguration
    CalendarEvent toEntity(CalendarEventDTO calendarEventDTO);

    @Override
    @Mapping(source = "id", target = "codigoEvento")
    CalendarEventDTO toDto(CalendarEvent calendarEvent);

}
