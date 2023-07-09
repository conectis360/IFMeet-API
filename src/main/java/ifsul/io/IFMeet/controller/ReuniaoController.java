package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.CursoDTO;
import ifsul.io.IFMeet.controller.dto.ReuniaoDTO;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.mapper.CursoMapper;
import ifsul.io.IFMeet.mapper.ReuniaoMapper;
import ifsul.io.IFMeet.service.CursoService;
import ifsul.io.IFMeet.service.ReuniaoService;
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
@RequestMapping("/v1/reuniao")
public class ReuniaoController {
    @Autowired
    ReuniaoMapper reuniaoMapper;
    @Autowired
    ReuniaoService reuniaoService;

    @ApiOperation(value = "Busca todas as reuniões", notes = "Retorna todas as reuniões cadastradas no banco")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReuniaoDTO>> findAll() {
        log.debug("entrou findAll");
        List<ReuniaoDTO> listaReuniaoDTO = new ArrayList<>();
        List<Reuniao> listaReuniao = reuniaoService.findAll();

        for (Reuniao curso : listaReuniao) {
            listaReuniaoDTO.add(reuniaoMapper.toDto(curso));
        }

        return ResponseEntity.ok(listaReuniaoDTO);
    }

    @ApiOperation(value = "Retornar Curso por ID", notes = "Cadastra um novo curso")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReuniaoDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou registrarOrientador");
        Optional<Reuniao> reuniaoOptional = reuniaoService.findById(id);
        Reuniao reuniao = reuniaoOptional.orElse(null);
        ReuniaoDTO reuniaoDTO = reuniaoMapper.toDto(reuniao);
        return ResponseEntity.ok(reuniaoDTO);
    }

    @ApiOperation(value = "Cadastrar Reuniao", notes = "Cadastra uma nova Reuniao")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping(value = "/cadastrarCurso", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarCurso(@RequestBody ReuniaoDTO reuniaoDTO) {
        log.debug("entrou registrarOrientador");
        Reuniao reuniao = reuniaoMapper.toEntity(reuniaoDTO);
        reuniaoService.save(reuniao);
        return ResponseEntity.ok().build();
    }

}
