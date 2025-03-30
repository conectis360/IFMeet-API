package ifsul.io.IFMeet.domain.calendarevent.repository;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent_;
import ifsul.io.IFMeet.domain.usuario.model.Usuario_;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CalendarEventSpecs {

    public static Specification<CalendarEvent> calendarEventFilter(CalendarEventFilterDto calendarEventFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (calendarEventFilterDto.getCodigoUsuario() != null) {
                predicates.add(root.get(CalendarEvent_.USUARIO).get(Usuario_.ID).in(calendarEventFilterDto.getCodigoUsuario()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
