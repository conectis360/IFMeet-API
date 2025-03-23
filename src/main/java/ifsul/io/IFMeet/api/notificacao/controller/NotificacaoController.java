package ifsul.io.IFMeet.api.notificacao.controller;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoDTO;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewDTO;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewFilterDto;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacoesDTO;
import ifsul.io.IFMeet.api.notificacao.mapper.NotificacaoMapper;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.service.NotificacaoService;
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
@RequestMapping("/v1/notificacao")
@CrossOrigin(origins = "*")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;
    private final NotificacaoMapper notificacaoMapper;

    @ApiOperation(value = "Retornar todas as notificações", notes = "Retornar todas as notificações por filtro")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<NotificacaoViewDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject NotificacaoViewFilterDto notificacaoViewFilterDto) {
        log.debug("into findAll");
        return notificacaoService.findAll(request, notificacaoViewFilterDto);
    }

    @ApiOperation(value = "Retornar notificação por ID", notes = "Retorna notificação por ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NotificacaoDTO> findById(@PathVariable("id") Long id) {
        log.debug("into findById");
        Optional<Notificacao> notificacaoOptional = notificacaoService.findById(id);
        Notificacao notificacao = notificacaoOptional.orElse(null);
        NotificacaoDTO notificacaoDTO = notificacaoMapper.toDto(notificacao);
        return ResponseEntity.ok(notificacaoDTO);
    }

    @ApiOperation(value = "Retornar todas as notificações", notes = "Retornar todas as notificações por filtro")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/count", produces = {MediaType.APPLICATION_JSON_VALUE})
    public NotificacoesDTO findCountNotificacoesTipo() {
        log.debug("into findCountNotificacoes");
        return notificacaoService.quantidadeDeNotificacoesPorTipo();
    }
}
