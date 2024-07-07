package ifsul.io.IFMeet.repository;


import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.domain.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReuniaoRepository extends JpaRepository<Reuniao,Long> {

    List<Reuniao> findAllByOrientadorId(Long orientador);

}
