package ifsul.io.IFMeet.domain.trabalho.repository;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoFilterDto;
import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.curso.model.Curso_;
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
public class TrabalhoSpecs {

    /**
     * Cria uma especificação (Specification) para filtrar trabalhos com base nos critérios fornecidos
     * e no tipo de usuário logado.
     *
     * <p>Esta função aplica diferentes filtros dependendo da role do usuário:
     * <ul>
     *   <li>Para usuários com role ROLE_ORIENTANDO, filtra apenas por trabalhos do próprio aluno</li>
     *   <li>Para usuários com role ROLE_ORIENTADOR, filtra por trabalhos onde o usuário é orientador</li>
     *   <li>Para usuários com role ROLE_ADMIN, aplica todos os filtros fornecidos</li>
     * </ul>
     *
     * @param trabalhoFilterDto DTO contendo os critérios de filtro (opcional para não-admins)
     * @param usuarioLogado     O usuário atualmente logado no sistema
     * @return Uma especificação (Specification) que pode ser usada para filtrar trabalhos
     * @throws IllegalArgumentException se o usuário logado for nulo
     */
    public static Specification<Trabalho> trabalhoFilter(
            TrabalhoFilterDto trabalhoFilterDto, Usuario usuarioLogado) {

        // Validação de parâmetros
        Objects.requireNonNull(usuarioLogado, "O usuário logado não pode ser nulo");

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Aplica filtros específicos com base na role do usuário
            if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ORIENTANDO)) {
                // Para orientandos, filtra apenas por trabalhos do próprio aluno
                Optional.ofNullable(usuarioLogado.getId())
                        .ifPresent(codigo -> predicates.add(
                                root.get(Trabalho_.ALUNO)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
            } else if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ORIENTADOR)) {
                // Para orientadores, filtra por trabalhos onde o usuário é orientador
                Optional.ofNullable(usuarioLogado.getId())
                        .ifPresent(codigo -> predicates.add(
                                root.get(Trabalho_.ORIENTADOR)
                                        .get(Usuario_.ID)
                                        .in(codigo)));
            } else if (UsuarioService.possuiRoleStatic(usuarioLogado, Role.ROLE_ADMIN)) {
                // Para administradores, aplica todos os filtros fornecidos
                Optional.ofNullable(trabalhoFilterDto)
                        .ifPresent(dto -> {
                            if (dto.getCodigoTrabalho() != null) {
                                predicates.add(root.get(Trabalho_.ID).in(dto.getCodigoTrabalho()));
                            }
                            if (dto.getCodigoOrientador() != null) {
                                predicates.add(
                                        root.get(Trabalho_.ORIENTADOR)
                                                .get(Usuario_.ID)
                                                .in(dto.getCodigoOrientador()));
                            }
                            if (dto.getCodigoAluno() != null) {
                                predicates.add(
                                        root.get(Trabalho_.ALUNO)
                                                .get(Usuario_.ID)
                                                .in(dto.getCodigoAluno()));
                            }
                            if (dto.getCodigoCurso() != null) {
                                predicates.add(
                                        root.get(Trabalho_.CURSO)
                                                .get(Curso_.ID)
                                                .in(dto.getCodigoCurso()));
                            }
                        });
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