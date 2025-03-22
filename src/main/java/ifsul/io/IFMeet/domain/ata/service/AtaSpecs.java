package ifsul.io.IFMeet.domain.ata.service;

import ifsul.io.IFMeet.api.ata.dto.AtaFilterDto;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.ata.model.Ata_;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao_;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho_;
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
public class AtaSpecs {

    public static Specification<Ata> ataFilter(AtaFilterDto ataFilterDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (ataFilterDto.getCodigoReuniao() != null) {
                predicates.add(root.get(Ata_.REUNIAO).get(Reuniao_.ID).in(ataFilterDto.getCodigoReuniao()));
            }


            if (ataFilterDto.getCodigoTrabalho() != null) {
                predicates.add(root.get(Ata_.REUNIAO).get(Reuniao_.TRABALHO).get(Trabalho_.ID).in(ataFilterDto.getCodigoTrabalho()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
