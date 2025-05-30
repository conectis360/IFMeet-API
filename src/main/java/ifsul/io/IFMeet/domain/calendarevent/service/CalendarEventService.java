package ifsul.io.IFMeet.domain.calendarevent.service;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventDTO;
import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.api.calendarevent.mapper.CalendarEventMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventRepository;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventSpecs;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.model.StatusEnum;
import ifsul.io.IFMeet.domain.notificacao.model.TipoNotificacaoEnum;
import ifsul.io.IFMeet.domain.notificacao.service.NotificacaoService;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.domain.trabalho.service.TrabalhoService;
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
import javax.transaction.Transactional;
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
    private final TrabalhoService trabalhoService;
    private final NotificacaoService notificacaoService;

    /**
     * Busca eventos de calendário paginados com filtros opcionais.
     *
     * @param request                Parâmetros de paginação (nº página, tamanho)
     * @param calendarEventFilterDto Filtros para pesquisa (opcional)
     * @return Resposta paginada com eventos e metadados
     */
    public DefaultPaginationResponse<CalendarEventDTO> findAll(DefaultRequestParams request, CalendarEventFilterDto calendarEventFilterDto) {
        log.debug("into findAll method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        Page<CalendarEventDTO> pageResult = repository
                .findAll(CalendarEventSpecs.calendarEventFilter(calendarEventFilterDto, usuario), pageRequestHelper.getPageRequest(request))
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

        // Busca o usuário logado
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        calendarEvent.setUsuario(usuario);

        // Verifica se há um trabalho associado
        if (calendarEvent.getTrabalho() != null && calendarEvent.getTrabalho().getId() != null) {
            // Busca o trabalho do banco de dados
            Trabalho trabalho = trabalhoService.findTrabalhoById(calendarEvent.getTrabalho().getId());

            // Define o trabalho recuperado do banco de dados
            calendarEvent.setTrabalho(trabalho);
        } else {
            throw new BusinessException(messages.get("trabalho.nao-encontrado"));
        }


        notificacaoService.criarNotificacoes(calendarEvent.getTrabalho(), TipoNotificacaoEnum.REUNIAO, messages.get("notificacoes.evento-criado", calendarEvent.getTitulo()));
        return repository.save(calendarEvent);
    }

    /**
     * deleta um evento no calendário
     */
    public void delete(Long id) {
        log.debug("into delete method");
        repository.deleteById(id);
    }

    /**
     * responde um evento no calendário
     */
    public void resposta(Long id, Boolean resposta) {
        log.debug("into resposta method");
        CalendarEvent evento = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento não encontrada com o ID: " + id));
        Status status = new Status();

        if (resposta) {
            status.setId(StatusEnum.ACEITA.getId());
            notificacaoService.criarNotificacoes(
                    evento.getTrabalho(),
                    TipoNotificacaoEnum.REUNIAO,
                    messages.get("notificacoes.evento-aceito", evento.getTitulo()
                    ));
        } else {
            status.setId(StatusEnum.CANCELADA.getId());
            notificacaoService.criarNotificacoes(
                    evento.getTrabalho(),
                    TipoNotificacaoEnum.REUNIAO,
                    messages.get("notificacoes.evento-recusado", evento.getTitulo()
                    ));
        }
        evento.setStatus(status);
        repository.save(evento);
    }

    /**
     * Atualiza um evento existente no calendário.
     *
     * @param id            ID do evento a ser atualizado.
     * @param calendarEvent Objeto com os novos dados do evento.
     * @return Optional contendo o evento atualizado, ou um Optional vazio se o evento não for encontrado.
     */
    @Transactional
    public Optional<CalendarEvent> update(Long id, CalendarEvent calendarEvent) {
        log.debug("into update method for id: {}", id);

        // Busca o evento existente pelo ID
        Optional<CalendarEvent> existingEventOptional = repository.findById(id);

        return existingEventOptional.map(existingEvent -> {
            Usuario usuarioLogado = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin()
                    .orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));

            // Verifica se o evento pertence aos usuários do trabalho
            if (!existingEvent.getTrabalho().getOrientador().equals(usuarioLogado) || !existingEvent.getTrabalho().getAluno().equals(usuarioLogado)) {
                throw new BusinessException(messages.get("evento.nao-pertence-usuario"));
            }

            // Salva as alterações no banco de dados
            return repository.save(calendarEvent);
        });
    }
}

