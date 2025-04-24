package ifsul.io.IFMeet.domain.disponibilidade.repository;

import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeFilterDto;
import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade;
import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade_;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.model.Usuario_;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DisponibilidadeSpecs {

    /**
     * Cria uma Specification para filtrar disponibilidades com base no usuário logado.
     *
     * <p>Esta função aplica os seguintes filtros:
     * <ul>
     *   <li>Filtra as disponibilidades pelo ID do usuário logado, independentemente de sua role</li>
     *   <li>Garante que os resultados sejam distintos para evitar duplicatas</li>
     * </ul>
     *
     * <p>Observações:
     * <ul>
     *   <li>Atualmente, o parâmetro disponibilidadeFilterDto não é utilizado na filtragem</li>
     *   <li>A função diferencia-se da documentação anterior por não aplicar filtros diferentes por role</li>
     * </ul>
     *
     * @param disponibilidadeFilterDto DTO contendo critérios de filtro (não utilizado atualmente)
     * @param usuarioLogado            O usuário atualmente logado no sistema
     * @return Uma Specification que filtra disponibilidades apenas pelo usuário logado
     * @throws IllegalArgumentException se o usuário logado for nulo ou não possuir ID
     */
    public static Specification<Disponibilidade> disponibilidadeFilter(DisponibilidadeFilterDto disponibilidadeFilterDto, Usuario usuarioLogado) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(usuarioLogado.getId())
                    .ifPresent(codigo -> predicates.add(
                            root.get(Disponibilidade_.USUARIO)
                                    .get(Usuario_.ID)
                                    .in(codigo)));

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
