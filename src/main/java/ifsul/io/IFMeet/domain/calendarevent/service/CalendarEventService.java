package ifsul.io.IFMeet.domain.calendarevent.service;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventDTO;
import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.api.calendarevent.mapper.CalendarEventMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventRepository;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventSpecs;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarEventService {

    private final CalendarEventRepository repository;
    private final CalendarEventMapper calendarEventMapper;
    private final Messages messages;
    private final UsuarioService usuarioService;
    private final PageRequestHelper pageRequestHelper;

    /**
     * Busca eventos de calendário paginados com filtros opcionais.
     *
     * @param request                Parâmetros de paginação (nº página, tamanho)
     * @param calendarEventFilterDto Filtros para pesquisa (opcional)
     * @return Resposta paginada com eventos e metadados
     */
    public DefaultPaginationResponse<CalendarEventDTO> findAll(DefaultRequestParams request, CalendarEventFilterDto calendarEventFilterDto) {
        log.debug("into findAll method");
        Page<CalendarEventDTO> pageResult = repository
                .findAll(CalendarEventSpecs.calendarEventFilter(calendarEventFilterDto), pageRequestHelper.getPageRequest(request))
                .map(calendarEventMapper::toDto);

        List<CalendarEventDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<CalendarEventDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    /**
     * Retorna todos os eventos de calendário sem paginação.
     *
     * @return Lista completa de eventos convertidos para DTO
     */
    public List<CalendarEventDTO> findAll() {
        log.debug("into findAll method");
        return Optional.ofNullable(repository.findAll())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(calendarEventMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retorna um evento de calendário por ID.
     *
     * @return um evento de calendario
     */
    public Optional<CalendarEvent> findById(Long id) {
        log.debug("into findById method");
        return repository.findById(id);
    }

    public CalendarEvent findByIdOrFail(Long id) {
        log.debug("into findByIdOrFail method, id: {}", id);
        return this.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CalendarEvent não encontrado com o ID: " + id));
    }

    /**
     * Salva um evento no calendário
     *
     * @return evento salvo
     */
    public CalendarEvent save(CalendarEvent calendarEvent) {
        log.debug("into save method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        calendarEvent.setUsuario(usuario);
        return repository.save(calendarEvent);
    }

    /**
     * deleta um evento no calendário
     */
    public void delete(Long id) {
        log.debug("into delete method");
        repository.deleteById(id);
    }
}

