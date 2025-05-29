package ifsul.io.IFMeet.domain.notificacao.service;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewDTO;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewFilterDto;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacoesDTO;
import ifsul.io.IFMeet.api.notificacao.mapper.NotificacaoViewMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.model.TipoNotificacao;
import ifsul.io.IFMeet.domain.notificacao.model.TipoNotificacaoEnum;
import ifsul.io.IFMeet.domain.notificacao.repository.NotificacaoRepository;
import ifsul.io.IFMeet.domain.notificacao.repository.NotificacaoViewRepository;
import ifsul.io.IFMeet.domain.notificacao.repository.TipoNotificacaoRepository;
import ifsul.io.IFMeet.domain.notificacao.spec.NotificacaoViewSpecs;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacaoService {


    private final NotificacaoRepository notificacaoRepository;
    private final NotificacaoViewRepository notificacaoViewRepository;
    private final UsuarioService usuarioService;
    private final TipoNotificacaoRepository tipoNotificacaoRepository;
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

    public NotificacoesDTO quantidadeDeNotificacoesPorTipo() {
        log.debug("into quantidadeDeNotificacoesPorTipo method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));

        return NotificacoesDTO.builder()
                .quantidadeNotificacoesDtoList(notificacaoRepository.countNotificacoesPorUsuarioETipo(usuario.getId()))
                .total(this.quantidadeNotificacoes(usuario.getId()))
                .build();
    }

    public Long quantidadeNotificacoes(Long usuarioId) {
        return notificacaoRepository.quantidadeDeNotificacoesPorUsuario(usuarioId);
    }

    /**
     * Cria notificações para o aluno e orientador de um trabalho
     *
     * @param trabalho O trabalho relacionado à notificação
     * @param tipo     O tipo de notificação a ser criada
     * @throws IllegalArgumentException se o trabalho ou tipo forem nulos
     */
    @Transactional
    public void criarNotificacoes(Trabalho trabalho, TipoNotificacaoEnum tipo) {
        log.debug("into criarNotificacoes method");
        Objects.requireNonNull(trabalho, "Trabalho não pode ser nulo");
        Objects.requireNonNull(tipo, "Tipo de notificação não pode ser nulo");

        TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findById(tipo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de notificação inválido"));

        Optional.ofNullable(trabalho.getAluno())
                .ifPresent(aluno -> criarNotificacao(aluno, tipoNotificacao, trabalho));

        Optional.ofNullable(trabalho.getOrientador())
                .ifPresent(orientador -> criarNotificacao(orientador, tipoNotificacao, trabalho));
    }

    /**
     * Cria uma notificação individual para um usuário
     *
     * @param usuario  O usuário que receberá a notificação
     * @param tipo     O tipo de notificação
     * @param trabalho O trabalho relacionado
     */
    private void criarNotificacao(Usuario usuario, TipoNotificacao tipo, Trabalho trabalho) {
        Notificacao notificacao = new Notificacao();
        notificacao.setData(LocalDateTime.now());
        notificacao.setConteudo(String.format("Nova %s para o trabalho %s",
                tipo.getTipoNotificacao().toLowerCase(),
                trabalho.getTitulo()));
        notificacao.setUsuario(usuario);
        notificacao.setTipoNotificacao(tipo);

        notificacaoRepository.save(notificacao);
    }
}
