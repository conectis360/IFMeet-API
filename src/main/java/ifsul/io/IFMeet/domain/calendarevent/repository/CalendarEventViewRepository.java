package ifsul.io.IFMeet.domain.calendarevent.repository;

import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEventView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CalendarEventViewRepository extends JpaRepository<CalendarEventView, Long>, JpaSpecificationExecutor<CalendarEventView> {
}
