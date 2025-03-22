package ifsul.io.IFMeet.api.tarefa.controller;

import ifsul.io.IFMeet.api.tarefa.dto.TarefaDTO;
import ifsul.io.IFMeet.api.tarefa.dto.TarefaFilterDto;
import ifsul.io.IFMeet.api.tarefa.mapper.TarefaMapper;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa;
import ifsul.io.IFMeet.domain.tarefa.service.TarefaService;
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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/v1/tarefa")
public class TarefaController {

    private final TarefaMapper tarefaMapper;

    private final TarefaService tarefaService;

    @ApiOperation(value = "Retornar todas tarefas", notes = "Retornar todas tarefas")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<TarefaDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject TarefaFilterDto tarefaFilterDto) {
        log.debug("findAll");
        return tarefaService.findAll(request, tarefaFilterDto);
    }

    @ApiOperation(value = "Retornar tarefa por ID", notes = "Retornar tarefa por ID")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TarefaDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou findById");
        Optional<Tarefa> statusOptional = tarefaService.findById(id);
        Tarefa tarefa = statusOptional.orElse(null);
        TarefaDTO tarefaDTO = tarefaMapper.toDto(tarefa);
        return ResponseEntity.ok(tarefaDTO);
    }

    @ApiOperation(value = "Salva Status", notes = "Salva uma Status")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody TarefaDTO tarefaDTO) {
        log.debug("into save");
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaService.save(tarefa);
        return ResponseEntity.ok().build();
    }
}
