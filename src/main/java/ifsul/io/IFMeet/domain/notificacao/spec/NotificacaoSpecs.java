package ifsul.io.IFMeet.domain.notificacao.spec;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoFilterDto;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao_;
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
public class NotificacaoSpecs {

    public static Specification<Notificacao> notificacaoFilter(NotificacaoFilterDto notificacaoFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (notificacaoFilterDto.getCodigoUsuario() != null) {
                predicates.add(root.get(Notificacao_.USUARIO).get(Usuario_.ID).in(notificacaoFilterDto.getCodigoUsuario()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
