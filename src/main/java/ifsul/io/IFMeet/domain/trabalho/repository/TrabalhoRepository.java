package ifsul.io.IFMeet.domain.trabalho.repository;

import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho,Long>, JpaSpecificationExecutor<Trabalho> {

    List<Trabalho> findAllByOrientadorId(Long orientador);
}
