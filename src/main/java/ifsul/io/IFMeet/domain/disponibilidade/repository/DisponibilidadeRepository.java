package ifsul.io.IFMeet.domain.disponibilidade.repository;


import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade,Long>, JpaSpecificationExecutor<Disponibilidade> {

}
