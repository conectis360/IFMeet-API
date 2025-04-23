package ifsul.io.IFMeet.domain.calendarevent.repository;

import ifsul.io.IFMeet.api.calendarevent.dto.CalendarEventFilterDto;
import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent_;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
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
public class CalendarEventSpecs {

    /**
     * Cria uma especificação (Specification) para filtrar eventos de calendário com base nos critérios fornecidos
     * e no tipo de usuário logado.
     *
     * <p>Esta função aplica diferentes filtros dependendo da role do usuário:
     * <ul>
     *   <li>Para usuários com role ROLE_ORIENTANDO, filtra por código de trabalho</li>
     *   <li>Para usuários com role ROLE_ORIENTADOR, filtra por código de orientador</li>
     *   <li>Para usuários com role ROLE_ADMIN, aplica todos os filtros fornecidos</li>
     * </ul>
     *
     * @param calendarEventFilterDto DTO contendo os critérios de filtro
     * @param usuarioLogado O usuário atualmente logado no sistema
     * @return Uma especificação (Specification) que pode ser usada para filtrar eventos de calendário
     * @throws IllegalArgumentException se o DTO de filtro ou o usuário logado for nulo
     */
    public static Specification<CalendarEvent> calendarEventFilter(
            CalendarEventFilterDto calendarEventFilterDto, Usuario usuarioLogado) {

        // Validação de parâmetros
        Objects.requireNonNull(calendarEventFilterDto, "O DTO de filtro não pode ser nulo");
        Objects.requireNonNull(usuarioLogado, "O usuário logado não pode ser nulo");

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Aplica filtros específicos com base na role do usuário
            if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ORIENTANDO)) {
                // Para orientandos, filtra por trabalho baseado no id do usuário
                Optional.ofNullable(usuarioLogado.getId())
                        .ifPresent(codigo -> predicates.add(
                                root.get(CalendarEvent_.TRABALHO)
                                        .get(Trabalho_.ALUNO)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
            } else if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ORIENTADOR)) {
                // Para orientadores, filtra por código de orientador baseado no id do usuário
                Optional.ofNullable(usuarioLogado.getId())
                        .ifPresent(codigo -> predicates.add(
                                root.get(CalendarEvent_.TRABALHO)
                                        .get(Trabalho_.ORIENTADOR)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
            } else if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ADMIN)) {
                // Para administradores, aplica todos os filtros fornecidos
                Optional.ofNullable(calendarEventFilterDto.getCodigoUsuario())
                        .ifPresent(codigo -> predicates.add(
                                root.get(CalendarEvent_.USUARIO)
                                        .get(Usuario_.ID)
                                        .in(codigo)));

                Optional.ofNullable(calendarEventFilterDto.getCodigoTrabalho())
                        .ifPresent(codigo -> predicates.add(
                                root.get(CalendarEvent_.TRABALHO)
                                        .get(Trabalho_.ID)
                                        .in(codigo)));

                Optional.ofNullable(calendarEventFilterDto.getCodigoOrientador())
                        .ifPresent(codigo -> predicates.add(
                                root.get(CalendarEvent_.TRABALHO)
                                        .get(Trabalho_.ORIENTADOR)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
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
