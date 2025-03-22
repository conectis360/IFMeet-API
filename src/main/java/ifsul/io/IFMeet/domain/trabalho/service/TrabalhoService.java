package ifsul.io.IFMeet.domain.trabalho.service;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoFilterDto;
import ifsul.io.IFMeet.api.trabalho.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.domain.trabalho.repository.TrabalhoRepository;
import ifsul.io.IFMeet.domain.trabalho.repository.TrabalhoSpecs;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrabalhoService {

    private final TrabalhoRepository trabalhoRepository;
    private final TrabalhoMapper trabalhoMapper;
    private final UsuarioService usuarioService;
    private final PageRequestHelper pageRequestHelper;
    private final Messages messages;

    public Trabalho findTrabalhoById(Long codigoTrabalho) {
        log.debug("into findTrabalhoById method");
        Optional<Trabalho> trabalhoOpt = trabalhoRepository.findById(codigoTrabalho);
        Trabalho trabalho = trabalhoOpt.orElse(null);
        return trabalho;
    }

    public DefaultPaginationResponse<TrabalhoDTO> findAll(DefaultRequestParams request, TrabalhoFilterDto trabalhoFilterDto) {
        log.debug("into findAll method");
        Page<TrabalhoDTO> pageResult = trabalhoRepository
                .findAll(TrabalhoSpecs.trabalhoFilter(trabalhoFilterDto), pageRequestHelper.getPageRequest(request))
                .map(trabalhoMapper::toDto);

        List<TrabalhoDTO> listaTrabalhos = pageResult.getContent();

        return DefaultPaginationResponse.<TrabalhoDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaTrabalhos)
                .build();
    }

    @Transactional
    public void save(Trabalho trabalho) {
        log.debug("into save method");
        this.validaOrientando(trabalho.getAluno().getId());
        trabalhoRepository.save(trabalho);
    }

    private void validaOrientando(Long codigoOrientando) {
        log.debug("into validaOrientando method");
        Trabalho trabalho = this.findTrabalhoById(codigoOrientando);
        if (trabalho != null) {
            throw new BusinessException(messages.get("trabalho.orientando-ja-possui-trabalho"));
        }
    }

}
