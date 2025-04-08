package ifsul.io.IFMeet.domain.disponibilidade.repository;

import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeFilterDto;
import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade;
import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade_;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao_;
import ifsul.io.IFMeet.domain.usuario.model.Usuario_;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DisponibilidadeSpecs {

    public static Specification<Disponibilidade> disponibilidadeFilter(DisponibilidadeFilterDto disponibilidadeFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (disponibilidadeFilterDto.getCodigoUsuario() != null) {
                predicates.add(root.get(Disponibilidade_.USUARIO).get(Usuario_.ID).in(disponibilidadeFilterDto.getCodigoUsuario()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
