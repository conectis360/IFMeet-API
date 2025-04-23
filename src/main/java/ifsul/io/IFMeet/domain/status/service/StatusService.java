package ifsul.io.IFMeet.domain.status.service;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventDTO;
import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.api.status.dto.StatusDTO;
import ifsul.io.IFMeet.api.status.dto.StatusFilterDto;
import ifsul.io.IFMeet.api.status.mapper.StatusMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.calendarevent.repository.CalendarEventSpecs;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.status.repository.StatusRepository;
import ifsul.io.IFMeet.domain.status.repository.StatusSpecs;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusService {


    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;
    private final PageRequestHelper pageRequestHelper;

    private final Messages messages;

    public Optional<Status> findById(Long codigoStatus) {
        return statusRepository.findById(codigoStatus);
    }

    /**
     * Busca eventos de calendário paginados com filtros opcionais.
     *
     * @param request                Parâmetros de paginação (nº página, tamanho)
     * @param statusFilterDto Filtros para pesquisa (opcional)
     * @return Resposta paginada com eventos e metadados
     */
    public DefaultPaginationResponse<StatusDTO> findAll(DefaultRequestParams request, StatusFilterDto statusFilterDto) {
        log.debug("into findAll method");
        Page<StatusDTO> pageResult = statusRepository
                .findAll(StatusSpecs.statusFilter(statusFilterDto), pageRequestHelper.getPageRequest(request))
                .map(statusMapper::toDto);

        List<StatusDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<StatusDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    public void save(Status status) {
    }

}

