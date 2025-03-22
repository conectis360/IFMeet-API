package ifsul.io.IFMeet.domain.tarefa.repository;

import ifsul.io.IFMeet.api.tarefa.dto.TarefaFilterDto;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa_;
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
public class TarefaSpecs {

    public static Specification<Tarefa> tarefaFilter(TarefaFilterDto tarefaFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tarefaFilterDto.getCodigoTrabalho() != null) {
                predicates.add(root.get(Tarefa_.TRABALHO).in(tarefaFilterDto.getCodigoTrabalho()));
            }

            if (tarefaFilterDto.getCodigoOrientador() != null) {
                predicates.add(root.get(Tarefa_.TRABALHO).get(Trabalho_.ORIENTADOR).in(tarefaFilterDto.getCodigoOrientador()));
            }

            if (tarefaFilterDto.getCodigoAluno() != null) {
                predicates.add(root.get(Tarefa_.TRABALHO).get(Trabalho_.ALUNO).in(tarefaFilterDto.getCodigoAluno()));
            }

            query.distinct(true);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
