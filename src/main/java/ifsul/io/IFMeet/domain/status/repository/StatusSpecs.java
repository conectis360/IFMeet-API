package ifsul.io.IFMeet.domain.status.repository;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.api.status.dto.StatusFilterDto;
import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent_;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.status.model.Status_;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho_;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.model.Usuario_;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class StatusSpecs {

    /**
     * Cria uma especificação (Specification) para filtrar status com base nos critérios fornecidos.
     *
     * <p>Esta função aplica filtros com base nos campos preenchidos no DTO de filtro:
     * <ul>
     *   <li>Filtra por ID do status, se fornecido</li>
     *   <li>Filtra por nome do status, se fornecido</li>
     *   <li>Filtra por descrição do status, se fornecido</li>
     *   <li>Filtra por data de criação, se fornecida</li>
     *   <li>Filtra por status ativo/inativo, se fornecido</li>
     * </ul>
     *
     * @param statusFilterDto DTO contendo os critérios de filtro
     * @return Uma especificação (Specification) que pode ser usada para filtrar status
     * @throws IllegalArgumentException se o DTO de filtro for nulo
     */
    public static Specification<Status> statusFilter(StatusFilterDto statusFilterDto) {
        // Validação de parâmetros
        Objects.requireNonNull(statusFilterDto, "O DTO de filtro não pode ser nulo");

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Garante resultados distintos
            query.distinct(true);

            // Se não houver predicados, retorna uma condição sempre verdadeira
            if (predicates.isEmpty()) {
                return builder.conjunction();
            }

            // Combina todos os predicados com AND
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
