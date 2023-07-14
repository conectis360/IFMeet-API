package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.DocumentacaoDTO;
import ifsul.io.IFMeet.controller.dto.TarefaDTO;
import ifsul.io.IFMeet.domain.Documentacao;
import ifsul.io.IFMeet.domain.Tarefa;
import ifsul.io.IFMeet.mapper.DocumentacaoMapper;
import ifsul.io.IFMeet.mapper.TarefaMapper;
import ifsul.io.IFMeet.service.DocumentacaoService;
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
@RequestMapping("/v1/documentacao")
public class DocumentacaoController {
    @Autowired
    DocumentacaoMapper documentacaoMapper;
    @Autowired
    DocumentacaoService documentacaoService;

    @ApiOperation(value = "Busca todas as documentações", notes = "Retorna todas as documentações cadastradas no banco")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<DocumentacaoDTO>> findAll() {
        log.debug("entrou findAll");
        List<DocumentacaoDTO> listaDocumentacaoDTO = new ArrayList<>();
        List<Documentacao> listaDocumentacao = documentacaoService.findAll();

        for (Documentacao documentacao : listaDocumentacao) {
            listaDocumentacaoDTO.add(documentacaoMapper.toDto(documentacao));
        }

        return ResponseEntity.ok(listaDocumentacaoDTO);
    }

    @ApiOperation(value = "Retornar documentação por ID", notes = "Retorna uma documentação pelo id")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentacaoDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou findById");
        Optional<Documentacao> documentacaoOptional = documentacaoService.findById(id);
        Documentacao documentacao = documentacaoOptional.orElse(null);
        DocumentacaoDTO documentacaoDTO = documentacaoMapper.toDto(documentacao);
        return ResponseEntity.ok(documentacaoDTO);
    }

    @ApiOperation(value = "Registrar Documentação", notes = "Registra uma nova documentação")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping(value = "/cadastrarDocumentacao", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarDocumentacao(@RequestBody DocumentacaoDTO documentacaoDTO) {
        log.debug("entrou registrarDocumentacao");
        Documentacao documentacao = documentacaoMapper.toEntity(documentacaoDTO);
        documentacaoService.save(documentacao);
        return ResponseEntity.ok().build();
    }
}
