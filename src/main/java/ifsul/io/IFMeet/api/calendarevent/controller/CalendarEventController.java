package ifsul.io.IFMeet.api.calendarevent.controller;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventDTO;
import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.api.calendarevent.mapper.CalendarEventMapper;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.service.CalendarEventService;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/calendario")
@CrossOrigin(origins = "*")
public class CalendarEventController {

    private final CalendarEventService calendarEventService;
    private final CalendarEventMapper calendarEventMapper;

    @ApiOperation(value = "Retornar todas atas", notes = "Retornar todas atas")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<CalendarEventDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject CalendarEventFilterDto calendarEventFilterDto) {
        log.debug("into findAll");
        return calendarEventService.findAll(request, calendarEventFilterDto);
    }

    @ApiOperation(value = "Retornar Evento por ID", notes = "Retorna Evento por ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CalendarEventDTO> findById(@PathVariable("id") Long id) {
        log.debug("into findById");
        CalendarEvent evento = calendarEventService.findByIdOrFail(id);
        return ResponseEntity.ok(calendarEventMapper.toDto(evento));
    }

    @ApiOperation(value = "Salva Evento", notes = "Salva um Evento")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody CalendarEventDTO calendarEventDTO) {
        log.debug("into save");
        calendarEventService.save(calendarEventMapper.toEntity(calendarEventDTO));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update Evento", notes = "Atualiza uma Evento")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(@RequestBody CalendarEventDTO calendarEventDTO) {
        log.debug("into save");
        calendarEventService.save(calendarEventMapper.toEntity(calendarEventDTO));
        return ResponseEntity.ok().build();
    }
}
