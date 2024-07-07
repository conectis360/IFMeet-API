package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.StatusDTO;
import ifsul.io.IFMeet.controller.dto.TarefaDTO;
import ifsul.io.IFMeet.domain.Status;
import ifsul.io.IFMeet.domain.Tarefa;
import ifsul.io.IFMeet.mapper.StatusMapper;
import ifsul.io.IFMeet.mapper.TarefaMapper;
import ifsul.io.IFMeet.service.StatusService;
import ifsul.io.IFMeet.service.TarefaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/v1/tarefa")
public class TarefaController {
    @Autowired
    TarefaMapper tarefaMapper;
    @Autowired
    TarefaService tarefaService;

    @ApiOperation(value = "Busca todas as reuniões", notes = "Retorna todas as reuniões cadastradas no banco")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TarefaDTO>> findAll() {
        log.debug("entrou findAll");
        List<TarefaDTO> listaTarefaDTO = new ArrayList<>();
        List<Tarefa> listaTarefa = tarefaService.findAll();

        for (Tarefa tarefa : listaTarefa) {
            listaTarefaDTO.add(tarefaMapper.toDto(tarefa));
        }

        return ResponseEntity.ok(listaTarefaDTO);
    }

    @ApiOperation(value = "Retornar Curso por ID", notes = "Cadastra um novo curso")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TarefaDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou findById");
        Optional<Tarefa> statusOptional = tarefaService.findById(id);
        Tarefa tarefa = statusOptional.orElse(null);
        TarefaDTO tarefaDTO = tarefaMapper.toDto(tarefa);
        return ResponseEntity.ok(tarefaDTO);
    }

    @ApiOperation(value = "Cadastrar Tarefa", notes = "Cadastra uma nova Tarefa")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping(value = "/cadastrarTarefa", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        log.debug("entrou cadastrarTarefa");
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaService.save(tarefa);
        return ResponseEntity.ok().build();
    }
}
