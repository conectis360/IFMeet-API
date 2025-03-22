package ifsul.io.IFMeet.domain.reuniao.repository;

import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoFilterDto;
import ifsul.io.IFMeet.domain.curso.model.Curso_;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
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
public class ReuniaoSpecs {

    public static Specification<Reuniao> reuniaoFilter(ReuniaoFilterDto reuniaoFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(reuniaoFilterDto.getCodigoOrientador() != null) {
                predicates.add(root.get(Reuniao_.ORIENTADOR).get(Usuario_.ID).in(reuniaoFilterDto.getCodigoOrientador()));
            }

            if(reuniaoFilterDto.getCodigoAluno() != null) {
                predicates.add(root.get(Reuniao_.ORIENTANDO).get(Usuario_.ID).in(reuniaoFilterDto.getCodigoAluno()));
            }

            if(reuniaoFilterDto.getCodigoCurso() != null) {
                predicates.add(root.get(Reuniao_.TRABALHO).get(Curso_.ID).in(reuniaoFilterDto.getCodigoCurso()));
            }

            if(reuniaoFilterDto.getCodigoTrabalho() != null) {
                predicates.add(root.get(Reuniao_.TRABALHO).in(reuniaoFilterDto.getCodigoTrabalho()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
