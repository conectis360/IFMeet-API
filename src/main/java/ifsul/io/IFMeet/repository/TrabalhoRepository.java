package ifsul.io.IFMeet.repository;

import ifsul.io.IFMeet.domain.Trabalho;
import ifsul.io.IFMeet.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrabalhoRepository extends JpaRepository<Trabalho,Long> {

    List<Trabalho> findAllByOrientadorId(Long orientador);
}
