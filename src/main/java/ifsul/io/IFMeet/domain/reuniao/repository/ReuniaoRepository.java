package ifsul.io.IFMeet.domain.reuniao.repository;


import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReuniaoRepository extends JpaRepository<Reuniao,Long>, JpaSpecificationExecutor<Reuniao> {

    List<Reuniao> findAllByOrientadorId(Long orientador);
    List<Reuniao> findAllByTrabalhoId(Long trabalho);

}
