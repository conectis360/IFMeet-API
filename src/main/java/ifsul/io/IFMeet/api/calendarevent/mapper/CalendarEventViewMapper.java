package ifsul.io.IFMeet.api.calendarevent.mapper;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventViewDTO;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEventView;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = BeanMapper.SPRING, uses = {
})
public interface CalendarEventViewMapper extends BeanMapper<CalendarEventView, CalendarEventViewDTO> {

    @Override
    CalendarEventView toEntity(CalendarEventViewDTO calendarEventDTO);

    @Override
    @InheritInverseConfiguration
    CalendarEventViewDTO toDto(CalendarEventView calendarEvent);

}