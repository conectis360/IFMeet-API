package ifsul.io.IFMeet.api.status.controller;

import ifsul.io.IFMeet.api.status.dto.StatusDTO;
import ifsul.io.IFMeet.api.status.mapper.StatusMapper;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.status.service.StatusService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/v1/status")
public class StatusController {

    private final StatusMapper statusMapper;
    private final StatusService statusService;

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
        log.debug("entrou findById");
        Optional<Status> statusOptional = statusService.findById(id);
        Status status = statusOptional.orElse(null);
        StatusDTO statusDTO = statusMapper.toDto(status);
        return ResponseEntity.ok(statusDTO);
    }

    @ApiOperation(value = "Salva Status", notes = "Salva uma Status")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody StatusDTO statusDTO) {
        log.debug("into save");
        Status status = statusMapper.toEntity(statusDTO);
        statusService.save(status);
        return ResponseEntity.ok().build();
    }
}
