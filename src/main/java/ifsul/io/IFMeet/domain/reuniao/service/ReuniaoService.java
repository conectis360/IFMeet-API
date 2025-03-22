package ifsul.io.IFMeet.domain.reuniao.service;

import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoDTO;
import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoFilterDto;
import ifsul.io.IFMeet.api.reuniao.mapper.ReuniaoMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import ifsul.io.IFMeet.domain.reuniao.repository.ReuniaoRepository;
import ifsul.io.IFMeet.domain.reuniao.repository.ReuniaoSpecs;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReuniaoService {

    private final ReuniaoMapper reuniaoMapper;
    private final UsuarioService usuarioService;
    private final PageRequestHelper pageRequestHelper;
    @Autowired
    ReuniaoRepository reuniaoRepository;
    @Autowired
    Messages messages;

    public Optional<Reuniao> findById(Long id) {
        return reuniaoRepository.findById(id);
    }

    public List<Reuniao> findAllByTrabalho(Long trabalhoId) {
        log.debug("into findAllByTrabalho method");
        List<Reuniao> listaTrabalhos = reuniaoRepository.findAllByTrabalhoId(trabalhoId);
        return listaTrabalhos;
    }

    public List<Reuniao> findAllByOrientador() {
        log.debug("into findAllByOrientador method");
        Usuario orientador = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        List<Reuniao> listaTrabalhos = reuniaoRepository.findAllByOrientadorId(orientador.getId());
        return listaTrabalhos;
    }

    public DefaultPaginationResponse<ReuniaoDTO> findAll(DefaultRequestParams request, ReuniaoFilterDto reuniaoFilterDto) {
        log.debug("into findAll method");
        Page<ReuniaoDTO> pageResult = reuniaoRepository
                .findAll(ReuniaoSpecs.reuniaoFilter(reuniaoFilterDto), pageRequestHelper.getPageRequest(request))
                .map(reuniaoMapper::toDto);

        List<ReuniaoDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<ReuniaoDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    public void save(Reuniao reuniao) {
        reuniaoRepository.save(reuniao);
    }

}
