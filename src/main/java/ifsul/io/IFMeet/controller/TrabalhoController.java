package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.TrabalhoDTO;
import ifsul.io.IFMeet.domain.Trabalho;
import ifsul.io.IFMeet.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.service.TrabalhoService;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1/trabalho")
public class TrabalhoController {

    @Autowired
    TrabalhoMapper trabalhoMapper;
    @Autowired
    TrabalhoService trabalhoService;

    @ApiOperation(value = "Busca por todos os trabalhos no banco", notes = "Busca no banco por todos os trabalhos existentes, normalmente utilizado para realizar relatórios ou acompanhamento")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TrabalhoDTO>> findAllTrabalhos() {
        log.debug("into findAllTrabalhos method");
        List<TrabalhoDTO> listaDeTrabalhosDTO = new ArrayList<>();
        List<Trabalho> listaDeTrabalhos = trabalhoService.findAllTrabalhos();

        for (Trabalho trabalho : listaDeTrabalhos) {
            listaDeTrabalhosDTO.add(trabalhoMapper.toDto(trabalho));
        }

        return ResponseEntity.ok(listaDeTrabalhosDTO);
    }

    @ApiOperation(value = "Busca por um trabalho especifico no banco", notes = "Busca por um trabalho especifico no banco, utilizado por orientandos e orientadores")
    @PreAuthorize("hasRole('ROLE_ORIENTANDO') or hasRole('ROLE_ORIENTADOR')")
    @GetMapping(value = "/findTrabalho/{codigoTrabalho}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TrabalhoDTO> findTrabalho(@PathVariable Long codigoTrabalho) {
        log.debug("into findTrabalho method");
        Trabalho trabalho = trabalhoService.findTrabalhoById(codigoTrabalho);
        TrabalhoDTO trabalhoDTO = trabalhoMapper.toDto(trabalho);
        return ResponseEntity.ok(trabalhoDTO);
    }

    @ApiOperation(value = "Cadastra um novo trabalho no banco", notes = "Cadastra um novo trabalho no banco de dados")
    @PreAuthorize("hasRole('ROLE_ORIENTADOR')")
    @PostMapping(value = "/cadastrarTrabalho", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void cadastrarTrabalho(@RequestBody TrabalhoDTO trabalhoDTO) {
        log.debug("into cadastrarTrabalho method");
        Trabalho trabalho = trabalhoMapper.toEntity(trabalhoDTO);
        trabalhoService.save(trabalho);
    }
}
