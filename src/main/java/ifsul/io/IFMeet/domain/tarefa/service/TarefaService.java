package ifsul.io.IFMeet.domain.tarefa.service;

import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoDTO;
import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoFilterDto;
import ifsul.io.IFMeet.api.tarefa.dto.TarefaDTO;
import ifsul.io.IFMeet.api.tarefa.dto.TarefaFilterDto;
import ifsul.io.IFMeet.api.tarefa.mapper.TarefaMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.reuniao.repository.ReuniaoSpecs;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa;
import ifsul.io.IFMeet.domain.tarefa.repository.TarefaRepository;
import ifsul.io.IFMeet.domain.tarefa.repository.TarefaSpecs;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final PageRequestHelper pageRequestHelper;
    private final TarefaMapper tarefaMapper;
    private final Messages messages;

    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public DefaultPaginationResponse<TarefaDTO> findAll(DefaultRequestParams request, TarefaFilterDto tarefaFilterDto) {
        log.debug("into findAll method");
        Page<TarefaDTO> pageResult = tarefaRepository
                .findAll(TarefaSpecs.tarefaFilter(tarefaFilterDto), pageRequestHelper.getPageRequest(request))
                .map(tarefaMapper::toDto);

        List<TarefaDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<TarefaDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    public void save(Tarefa tarefa) {
    }

}
