package ifsul.io.IFMeet.domain.notificacao.service;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoDTO;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoFilterDto;
import ifsul.io.IFMeet.api.notificacao.mapper.NotificacaoMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.model.QuantidadeNotificacoesDto;
import ifsul.io.IFMeet.domain.notificacao.repository.NotificacaoRepository;
import ifsul.io.IFMeet.domain.notificacao.spec.NotificacaoSpecs;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacaoService {


    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioService usuarioService;
    private final NotificacaoMapper notificacaoMapper;
    private final PageRequestHelper pageRequestHelper;
    private final Messages messages;

    public Optional<Notificacao> findById(Long codigoStatus) {
        return notificacaoRepository.findById(codigoStatus);
    }

    public DefaultPaginationResponse<NotificacaoDTO> findAll(DefaultRequestParams request, NotificacaoFilterDto notificacaoFilterDto) {
        log.debug("into findAll method");
        Page<NotificacaoDTO> pageResult = notificacaoRepository
                .findAll(NotificacaoSpecs.notificacaoFilter(notificacaoFilterDto), pageRequestHelper.getPageRequest(request))
                .map(notificacaoMapper::toDto);

        List<NotificacaoDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<NotificacaoDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    public void save(Notificacao status) {
    }

    public List<QuantidadeNotificacoesDto> quantidadeDeNotificacoesPorTipo() {
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        return notificacaoRepository.countNotificacoesPorUsuarioETipo(usuario.getId());
    }

    public Long quantidadeNotificacoes() {
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        return notificacaoRepository.quantidadeDeNotificacoesPorUsuario(usuario.getId());
    }

}
