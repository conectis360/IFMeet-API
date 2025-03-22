package ifsul.io.IFMeet.domain.trabalho.repository;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoFilterDto;
import ifsul.io.IFMeet.domain.curso.model.Curso_;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa_;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho_;
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
public class TrabalhoSpecs {

    public static Specification<Trabalho> trabalhoFilter(TrabalhoFilterDto trabalhoFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (trabalhoFilterDto.getCodigoTrabalho() != null) {
                predicates.add(root.get(Trabalho_.ID).in(trabalhoFilterDto.getCodigoTrabalho()));
            }

            if (trabalhoFilterDto.getCodigoOrientador() != null) {
                predicates.add(root.get(Tarefa_.TRABALHO).get(Usuario_.ID).in(trabalhoFilterDto.getCodigoOrientador()));
            }

            if (trabalhoFilterDto.getCodigoAluno() != null) {
                predicates.add(root.get(Tarefa_.TRABALHO).get(Usuario_.ID).in(trabalhoFilterDto.getCodigoAluno()));
            }

            if (trabalhoFilterDto.getCodigoCurso() != null) {
                predicates.add(root.get(Tarefa_.TRABALHO).get(Curso_.ID).in(trabalhoFilterDto.getCodigoCurso()));
            }

            query.distinct(true);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
