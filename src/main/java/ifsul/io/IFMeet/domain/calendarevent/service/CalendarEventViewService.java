package ifsul.io.IFMeet.domain.calendarevent.service;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventViewDTO;
import ifsul.io.IFMeet.api.calendarevent.mapper.CalendarEventViewMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventViewRepository;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventViewSpecs;
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

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarEventViewService {

    private final CalendarEventViewRepository repository;
    private final CalendarEventViewMapper calendarEventMapper;
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
    public DefaultPaginationResponse<CalendarEventViewDTO> findAll(DefaultRequestParams request, CalendarEventFilterDto calendarEventFilterDto) {
        log.debug("into findAll method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        Page<CalendarEventViewDTO> pageResult = repository
                .findAll(CalendarEventViewSpecs.calendarEventFilter(calendarEventFilterDto, usuario), pageRequestHelper.getPageRequest(request))
                .map(calendarEventMapper::toDto);

        List<CalendarEventViewDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<CalendarEventViewDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }
}

