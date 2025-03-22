package ifsul.io.IFMeet.api.notificacao.controller;

import ifsul.io.IFMeet.api.ata.dto.AtaDTO;
import ifsul.io.IFMeet.api.ata.dto.AtaFilterDto;
import ifsul.io.IFMeet.api.tarefa.dto.TarefaDTO;
import ifsul.io.IFMeet.api.tarefa.dto.TarefaFilterDto;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.api.ata.mapper.AtaMapper;
import ifsul.io.IFMeet.domain.ata.service.AtaService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/ata")
@CrossOrigin(origins = "*")
public class NotificacaoController {

    private final AtaService ataService;
    private final AtaMapper ataMapper;

    @ApiOperation(value = "Retornar todas atas", notes = "Retornar todas atas")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<AtaDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject AtaFilterDto ataFilterDto) {
        log.debug("into findAll");
        return ataService.findAll(request, ataFilterDto);
    }

    @ApiOperation(value = "Retornar Atas por ID", notes = "Retorna Ata por ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AtaDTO> findById(@PathVariable("id") Long id) {
        log.debug("into findById");
         Optional<Ata> ataOptional = ataService.findById(id);
        Ata ata = ataOptional.orElse(null);
        AtaDTO ataDTO = ataMapper.toDto(ata);
        return ResponseEntity.ok(ataDTO);
    }

    @ApiOperation(value = "Salva Ata", notes = "Salva uma Ata")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody AtaDTO ataDTO) {
        log.debug("into save");
        Ata ata = ataMapper.toEntity(ataDTO);
        ataService.save(ata);
        return ResponseEntity.ok().build();
    }
}
