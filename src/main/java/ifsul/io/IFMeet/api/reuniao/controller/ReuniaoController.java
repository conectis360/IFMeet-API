package ifsul.io.IFMeet.api.reuniao.controller;

import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoDTO;
import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoFilterDto;
import ifsul.io.IFMeet.api.reuniao.mapper.ReuniaoMapper;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import ifsul.io.IFMeet.domain.reuniao.service.ReuniaoService;
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
@RequestMapping("/v1/reuniao")
public class ReuniaoController {
    private final ReuniaoMapper reuniaoMapper;
    private final ReuniaoService reuniaoService;


    @ApiOperation(value = "Retornar Reunião por ID", notes = "Retornar Reunião por ID")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReuniaoDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou findById");
        Optional<Reuniao> reuniaoOptional = reuniaoService.findById(id);
        Reuniao reuniao = reuniaoOptional.orElse(null);
        ReuniaoDTO reuniaoDTO = reuniaoMapper.toDto(reuniao);
        return ResponseEntity.ok(reuniaoDTO);
    }

    @ApiOperation(value = "Retornar todas Reuniões", notes = "Retornar todas Reuniões")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<ReuniaoDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject ReuniaoFilterDto reuniaoFilterDto) {
        log.debug("findAll");
        return reuniaoService.findAll(request, reuniaoFilterDto);
    }


    @ApiOperation(value = "Salva Reuniao", notes = "Salva uma Reuniao")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody ReuniaoDTO reuniaoDTO) {
        log.debug("into save");
        Reuniao reuniao = reuniaoMapper.toEntity(reuniaoDTO);
        reuniaoService.save(reuniao);
        return ResponseEntity.ok().build();
    }

}
