package ifsul.io.IFMeet.api.documentacao.controller;

import ifsul.io.IFMeet.api.documentacao.dto.DocumentacaoDTO;
import ifsul.io.IFMeet.domain.documentacao.model.Documentacao;
import ifsul.io.IFMeet.api.documentacao.mapper.DocumentacaoMapper;
import ifsul.io.IFMeet.domain.documentacao.service.DocumentacaoService;
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
@RequestMapping("/v1/documentacao")
@CrossOrigin(origins = "*")
public class DocumentacaoController {
    private final DocumentacaoMapper documentacaoMapper;
    private final DocumentacaoService documentacaoService;

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
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentacaoDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou findById");
        Optional<Documentacao> documentacaoOptional = documentacaoService.findById(id);
        Documentacao documentacao = documentacaoOptional.orElse(null);
        DocumentacaoDTO documentacaoDTO = documentacaoMapper.toDto(documentacao);
        return ResponseEntity.ok(documentacaoDTO);
    }

    @ApiOperation(value = "Salva Documentação", notes = "Salva uma documentação")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody DocumentacaoDTO documentacaoDTO) {
        log.debug("into save");
        Documentacao documentacao = documentacaoMapper.toEntity(documentacaoDTO);
        documentacaoService.save(documentacao);
        return ResponseEntity.ok().build();
    }
}
