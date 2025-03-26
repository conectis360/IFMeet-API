package ifsul.io.IFMeet.domain.ata.service;

import ifsul.io.IFMeet.api.ata.dto.AtaDTO;
import ifsul.io.IFMeet.api.ata.dto.AtaFilterDto;
import ifsul.io.IFMeet.api.ata.mapper.AtaMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.ata.repository.AtaRepository;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtaService {

    private final AtaRepository ataRepository;
    private final AtaMapper ataMapper;
    private final Messages messages;
    private final PageRequestHelper pageRequestHelper;

    public Optional<Ata> findById(Long id) {
        log.debug("into findById method");
        return ataRepository.findById(id);
    }

    /**
     * Busca uma Ata pelo ID e lança uma EntityNotFoundException caso não encontre.
     * Utiliza Java 8 Optional para tratar a ausência do registro.
     *
     * @param id O ID da Ata a ser buscada.
     * @return A Ata encontrada.
     * @throws EntityNotFoundException se a Ata não for encontrada.
     */
    public Ata findByIdOrFail(Long id) {
        log.debug("into findByIdOrFail method, id: {}", id);
        return this.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ata não encontrada com o ID: " + id));
    }

    public DefaultPaginationResponse<AtaDTO> findAll(DefaultRequestParams request, AtaFilterDto ataFilterDto) {
        log.debug("into findAll method");
        Page<AtaDTO> pageResult = ataRepository
                .findAll(AtaSpecs.ataFilter(ataFilterDto), pageRequestHelper.getPageRequest(request))
                .map(ataMapper::toDto);

        List<AtaDTO> listaReunioes = pageResult.getContent();

        return DefaultPaginationResponse.<AtaDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaReunioes)
                .build();
    }

    public void save(Ata ata) {
        log.debug("into save method");
        ataRepository.save(ata);
    }

    public void update(Ata ata) {
        log.debug("into update method");
        ataRepository.save(ata);
    }

}
