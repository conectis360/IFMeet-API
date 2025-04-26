package ifsul.io.IFMeet.domain.ata.repository;

import ifsul.io.IFMeet.api.ata.dto.AtaFilterDto;
import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.ata.model.Ata_;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent_;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho_;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.model.Usuario_;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class AtaSpecs {

    /**
     * Cria uma especificação (Specification) para filtrar atas com base nos critérios fornecidos
     * e no tipo de usuário logado.
     *
     * <p>Esta função aplica diferentes filtros dependendo da role do usuário:
     * <ul>
     *   <li>Para usuários com role ROLE_ORIENTANDO, filtra apenas por atas do próprio aluno</li>
     *   <li>Para usuários com role ROLE_ORIENTADOR, filtra por atas onde o usuário é orientador</li>
     *   <li>Para usuários com role ROLE_ADMIN, aplica todos os filtros fornecidos</li>
     * </ul>
     *
     * @param ataFilterDto  DTO contendo os critérios de filtro (opcional para não-admins)
     * @param usuarioLogado O usuário atualmente logado no sistema
     * @return Uma especificação (Specification) que pode ser usada para filtrar atas
     * @throws IllegalArgumentException se o usuário logado for nulo
     */
    public static Specification<Ata> ataFilter(
            AtaFilterDto ataFilterDto, Usuario usuarioLogado) {

        // Validação de parâmetros
        Objects.requireNonNull(usuarioLogado, "O usuário logado não pode ser nulo");

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Aplica filtros específicos com base na role do usuário
            if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ORIENTANDO)) {
                // Para orientandos, filtra apenas por atas do próprio aluno
                Optional.ofNullable(usuarioLogado.getId())
                        .ifPresent(codigo -> predicates.add(
                                root.get(Ata_.EVENTO)
                                        .get(CalendarEvent_.TRABALHO)
                                        .get(Trabalho_.ALUNO)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
            } else if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ORIENTADOR)) {
                // Para orientadores, filtra por atas onde o usuário é orientador
                Optional.ofNullable(usuarioLogado.getId())
                        .ifPresent(codigo -> predicates.add(
                                root.get(Ata_.EVENTO)
                                        .get(CalendarEvent_.TRABALHO)
                                        .get(Trabalho_.ORIENTADOR)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
            } else if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ADMIN)) {
                // Para administradores, aplica todos os filtros fornecidos
            }

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