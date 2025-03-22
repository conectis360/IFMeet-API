package ifsul.io.IFMeet.domain.tcc.repository;


import ifsul.io.IFMeet.domain.tcc.model.TCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TCCRepository extends JpaRepository<TCC, Long>, JpaSpecificationExecutor<TCC> {
    Optional<TCC> findById(Long id);

    Optional<TCC> findByComentario(String comentario);
}
