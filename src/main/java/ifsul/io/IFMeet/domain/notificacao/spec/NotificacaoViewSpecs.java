package ifsul.io.IFMeet.domain.notificacao.spec;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewFilterDto;
import ifsul.io.IFMeet.domain.notificacao.model.NotificacaoView;
import ifsul.io.IFMeet.domain.notificacao.model.NotificacaoView_;
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
public class NotificacaoViewSpecs {

    public static Specification<NotificacaoView> notificacaoFilter(NotificacaoViewFilterDto notificacaoViewFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (notificacaoViewFilterDto.getCodigoUsuario() != null) {
                predicates.add(root.get(NotificacaoView_.USUARIO_ID).in(notificacaoViewFilterDto.getCodigoUsuario()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
