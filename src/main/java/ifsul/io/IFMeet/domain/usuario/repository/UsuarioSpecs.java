package ifsul.io.IFMeet.domain.usuario.repository;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioFilterDto;
import ifsul.io.IFMeet.domain.usuario.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsuarioSpecs {

    public static Specification<Usuario> usuarioFilter(UsuarioFilterDto usuarioFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (usuarioFilterDto.getCodigoUsuario() != null) {
                predicates.add(root.get(Usuario_.ID).in(usuarioFilterDto.getCodigoUsuario()));
            }

            if (usuarioFilterDto.getCodigoTipoUsuario() != null) {
                Join<Usuario, Roles> tipoUsuarioJoin = root.join("tipoUsuario", JoinType.INNER);

                Optional.ofNullable(usuarioFilterDto).ifPresent(filter -> {
                    Optional.ofNullable(filter.getCodigoTipoUsuario())
                            .ifPresent(nome -> predicates.add(tipoUsuarioJoin.get(TipoUsuario_.ID).in(filter.getCodigoTipoUsuario())));
                });
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
