package ifsul.io.IFMeet.api.disponibilidade.controller;

import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeDTO;
import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeFilterDto;
import ifsul.io.IFMeet.api.disponibilidade.mapper.DisponibilidadeMapper;
import ifsul.io.IFMeet.domain.disponibilidade.service.DisponibilidadeService;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/v1/disponibilidade")
public class DisponibilidadeController {
    private final DisponibilidadeService disponibilidadeService;
    private final DisponibilidadeMapper disponibilidadeMapper;


    @ApiOperation(value = "Retornar todas Reuniões", notes = "Retornar todas Reuniões")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<DisponibilidadeDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject DisponibilidadeFilterDto disponibilidadeFilterDto) {
        log.debug("findAll");
        return disponibilidadeService.findAll(request, disponibilidadeFilterDto);
    }


    @ApiOperation(value = "Salva disponibilidade", notes = "Salva uma disponibilidade")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody DisponibilidadeDTO disponibilidadeDTO) {
        log.debug("into save");
        disponibilidadeService.save(disponibilidadeMapper.toEntity(disponibilidadeDTO));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update disponibilidade", notes = "Atualiza uma disponibilidade")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(@RequestBody DisponibilidadeDTO disponibilidadeDTO) {
        log.debug("into update");
        disponibilidadeService.save(disponibilidadeMapper.toEntity(disponibilidadeDTO));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Excluir disponibilidade", notes = "Excluir uma disponibilidade")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.debug("into delete");
        disponibilidadeService.delete(id);
        return ResponseEntity.ok().build();
    }


}
