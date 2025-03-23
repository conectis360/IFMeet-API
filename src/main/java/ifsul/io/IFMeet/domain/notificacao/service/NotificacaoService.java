package ifsul.io.IFMeet.domain.notificacao.service;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewDTO;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewFilterDto;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacoesDTO;
import ifsul.io.IFMeet.api.notificacao.mapper.NotificacaoMapper;
import ifsul.io.IFMeet.api.notificacao.mapper.NotificacaoViewMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.repository.NotificacaoRepository;
import ifsul.io.IFMeet.domain.notificacao.repository.NotificacaoViewRepository;
import ifsul.io.IFMeet.domain.notificacao.spec.NotificacaoViewSpecs;
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
    private final NotificacaoViewRepository notificacaoViewRepository;
    private final UsuarioService usuarioService;
    private final NotificacaoMapper notificacaoMapper;
    private final NotificacaoViewMapper notificacaoViewMapper;
    private final PageRequestHelper pageRequestHelper;
    private final Messages messages;

    public Optional<Notificacao> findById(Long codigoStatus) {
        return notificacaoRepository.findById(codigoStatus);
    }

    public DefaultPaginationResponse<NotificacaoViewDTO> findAll(DefaultRequestParams request, NotificacaoViewFilterDto notificacaoFilterDto) {
        log.debug("into findAll method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        notificacaoFilterDto.setCodigoUsuario(usuario.getId());

        Page<NotificacaoViewDTO> pageResult = notificacaoViewRepository
                .findAll(NotificacaoViewSpecs.notificacaoFilter(notificacaoFilterDto), pageRequestHelper.getPageRequest(request))
                .map(notificacaoViewMapper::toDto);

        List<NotificacaoViewDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<NotificacaoViewDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    public void save(Notificacao status) {
    }

    public NotificacoesDTO quantidadeDeNotificacoesPorTipo() {
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));

        return NotificacoesDTO.builder()
                .quantidadeNotificacoesDtoList(notificacaoRepository.countNotificacoesPorUsuarioETipo(usuario.getId()))
                .total(this.quantidadeNotificacoes(usuario.getId()))
                .build();
    }

    public Long quantidadeNotificacoes(Long usuarioId) {
        return notificacaoRepository.quantidadeDeNotificacoesPorUsuario(usuarioId);
    }

}
