package ifsul.io.IFMeet.domain.disponibilidade.service;

import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeDTO;
import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeFilterDto;
import ifsul.io.IFMeet.api.disponibilidade.mapper.DisponibilidadeMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade;
import ifsul.io.IFMeet.domain.disponibilidade.repository.DisponibilidadeRepository;
import ifsul.io.IFMeet.domain.disponibilidade.repository.DisponibilidadeSpecs;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class DisponibilidadeService {

    private final DisponibilidadeMapper disponibilidadeMapper;
    private final PageRequestHelper pageRequestHelper;
    private final UsuarioService usuarioService;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final Messages messages;

    public DefaultPaginationResponse<DisponibilidadeDTO> findAll(DefaultRequestParams request, DisponibilidadeFilterDto disponibilidadeFilterDto) {
        log.debug("into findAll method");
        Page<DisponibilidadeDTO> pageResult = disponibilidadeRepository
                .findAll(DisponibilidadeSpecs.disponibilidadeFilter(disponibilidadeFilterDto), pageRequestHelper.getPageRequest(request))
                .map(disponibilidadeMapper::toDto);

        List<DisponibilidadeDTO> lista = pageResult.getContent();

        return DefaultPaginationResponse.<DisponibilidadeDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(lista)
                .build();
    }

    public void save(Disponibilidade disponibilidade) {
        log.debug("into save method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        disponibilidade.setUsuario(usuario);
        disponibilidadeRepository.save(disponibilidade);
    }

}
