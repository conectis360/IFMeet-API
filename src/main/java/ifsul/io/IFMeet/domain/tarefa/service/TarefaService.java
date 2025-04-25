package ifsul.io.IFMeet.domain.tarefa.service;

import ifsul.io.IFMeet.api.tarefa.dto.TarefaDTO;
import ifsul.io.IFMeet.api.tarefa.dto.TarefaFilterDto;
import ifsul.io.IFMeet.api.tarefa.mapper.TarefaMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa;
import ifsul.io.IFMeet.domain.tarefa.repository.TarefaRepository;
import ifsul.io.IFMeet.domain.tarefa.repository.TarefaSpecs;
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

import java.time.LocalDate;
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
    private final UsuarioService usuarioService;

    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public DefaultPaginationResponse<TarefaDTO> findAll(DefaultRequestParams request, TarefaFilterDto tarefaFilterDto) {
        log.debug("into findAll method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        Page<TarefaDTO> pageResult = tarefaRepository
                .findAll(TarefaSpecs.tarefaFilter(tarefaFilterDto, usuario), pageRequestHelper.getPageRequest(request))
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
        log.debug("into save method");
        tarefa.setDataInicio(LocalDate.now());
        tarefaRepository.save(tarefa);
    }

    public void update(Tarefa tarefa) {
        log.debug("into update method");
        if (tarefa == null) {
            log.warn("Tentativa de atualizar tarefa nula");
            throw new IllegalArgumentException("Tarefa não pode ser nula");
        }

        log.debug("Atualizando tarefa ID: {}", tarefa.getId());

        if (Boolean.TRUE.equals(tarefa.getFinalizada()) && tarefa.getDataFim() == null) {
            log.debug("Marcando tarefa como finalizada - definindo dataFim");
            tarefa.setDataFim(LocalDate.now());
        }

        // Persistência da tarefa atualizada
        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
        log.info("Tarefa ID: {} atualizada com sucesso", tarefaAtualizada.getId());
    }

}
