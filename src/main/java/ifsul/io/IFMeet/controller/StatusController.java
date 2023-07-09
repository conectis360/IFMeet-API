package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.ReuniaoDTO;
import ifsul.io.IFMeet.controller.dto.StatusDTO;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.domain.Status;
import ifsul.io.IFMeet.mapper.ReuniaoMapper;
import ifsul.io.IFMeet.mapper.StatusMapper;
import ifsul.io.IFMeet.service.ReuniaoService;
import ifsul.io.IFMeet.service.StatusService;
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
@RequestMapping("/v1/status")
public class StatusController {
    @Autowired
    StatusMapper statusMapper;
    @Autowired
    StatusService statusService;

    @ApiOperation(value = "Busca todas as status", notes = "Retorna todos os Status cadastrados no banco")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StatusDTO>> findAll() {
        log.debug("entrou findAll");
        List<StatusDTO> listaStatusDTO = new ArrayList<>();
        List<Status> listaStatus = statusService.findAll();

        for (Status status : listaStatus) {
            listaStatusDTO.add(statusMapper.toDto(status));
        }

        return ResponseEntity.ok(listaStatusDTO);
    }

    @ApiOperation(value = "Retornar Status", notes = "Retornar Status por ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StatusDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou registrarOrientador");
        Optional<Status> statusOptional = statusService.findById(id);
        Status status = statusOptional.orElse(null);
        StatusDTO statusDTO = statusMapper.toDto(status);
        return ResponseEntity.ok(statusDTO);
    }

    @ApiOperation(value = "Cadastrar Status", notes = "Cadastra um novo Status")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping(value = "/cadastrarStatus", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarStatus(@RequestBody StatusDTO statusDTO) {
        log.debug("entrou registrarCurso");
        Status status = statusMapper.toEntity(statusDTO);
        statusService.save(status);
        return ResponseEntity.ok().build();
    }
}
