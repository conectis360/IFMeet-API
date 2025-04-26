package ifsul.io.IFMeet.domain.ata.service;

import ifsul.io.IFMeet.api.ata.dto.AtaDTO;
import ifsul.io.IFMeet.api.ata.dto.AtaFilterDto;
import ifsul.io.IFMeet.api.ata.mapper.AtaMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.ata.repository.AtaRepository;
import ifsul.io.IFMeet.domain.ata.repository.AtaSpecs;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
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
    private final UsuarioService usuarioService;

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

    /**
     * Busca paginada de atas (reuniões) com filtros aplicáveis.
     *
     * <p>Este método realiza uma consulta paginada no repositório de atas, aplicando filtros opcionais
     * e convertendo os resultados para DTOs antes de retornar uma estrutura padronizada de paginação.</p>
     *
     * <p><b>Fluxo de execução:</b></p>
     * <ol>
     *   <li>Gera log de entrada no método</li>
     *   <li>Converte parâmetros de paginação para PageRequest</li>
     *   <li>Aplica filtros através de Specifications</li>
     *   <li>Executa consulta paginada no repositório</li>
     *   <li>Mapeia entidades para DTOs</li>
     *   <li>Constrói resposta padronizada de paginação</li>
     * </ol>
     *
     * @param request Parâmetros padrão de paginação (obrigatório)
     * @param ataFilterDto Filtros específicos para pesquisa de atas (opcional)
     * @return {@code DefaultPaginationResponse<AtaDTO>} contendo:
     *         <ul>
     *           <li>Lista de atas paginada</li>
     *           <li>Metadados de paginação</li>
     *         </ul>
     *
     * @throws IllegalArgumentException Se {@code request} for nulo
     * @throws DataAccessException Em caso de falha no acesso aos dados
     *
     * @see DefaultRequestParams
     * @see AtaFilterDto
     * @see DefaultPaginationResponse
     * @see PageRequestHelper#getPageRequest(DefaultRequestParams)
     *
     * @sample
     * // Exemplo de uso:
     * DefaultRequestParams params = new DefaultRequestParams(1, 10);
     * AtaFilterDto filtro = new AtaFilterDto("Reunião Mensal", "2023-01-01", "2023-12-31");
     *
     * DefaultPaginationResponse<AtaDTO> resultado = ataService.findAll(params, filtro);
     */
    public DefaultPaginationResponse<AtaDTO> findAll(DefaultRequestParams request, AtaFilterDto ataFilterDto) {
        log.debug("into findAll method");
        Usuario usuario = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        Page<AtaDTO> pageResult = ataRepository
                .findAll(AtaSpecs.ataFilter(ataFilterDto, usuario), pageRequestHelper.getPageRequest(request))
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
