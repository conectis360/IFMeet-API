package ifsul.io.IFMeet.api.curso.controller;

import ifsul.io.IFMeet.api.curso.dto.CursoDTO;
import ifsul.io.IFMeet.domain.curso.model.Curso;
import ifsul.io.IFMeet.api.curso.mapper.CursoMapper;
import ifsul.io.IFMeet.domain.curso.service.CursoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/curso")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoMapper cursoMapper;
    private final CursoService cursoService;

    @ApiOperation(value = "Busca todos os cursos", notes = "Retorna todos os cursos cadastrados no banco")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CursoDTO>> findAll() {
        log.debug("entrou findAll");
        List<CursoDTO> listaCursoDTO = new ArrayList<>();
        List<Curso> listaCurso = cursoService.findAll();

        for (Curso curso : listaCurso) {
            listaCursoDTO.add(cursoMapper.toDto(curso));
        }

        return ResponseEntity.ok(listaCursoDTO);
    }

    @ApiOperation(value = "Retornar Curso por ID", notes = "Cadastra um novo curso")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CursoDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou findById");
        Curso curso = cursoService.findById(id);
        CursoDTO cursoDTO = cursoMapper.toDto(curso);
        return ResponseEntity.ok(cursoDTO);
    }

    @ApiOperation(value = "Salvar Curso", notes = "Salvar curso")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody CursoDTO cursoDTO) {
        log.debug("into save");
        Curso curso = cursoMapper.toEntity(cursoDTO);
        cursoService.save(curso);
        return ResponseEntity.ok().build();
    }
}
