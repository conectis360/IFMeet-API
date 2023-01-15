package ifsul.io.IFMeet.repository;


import ifsul.io.IFMeet.domain.TCC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCRepository extends JpaRepository<TCC, Long> {
    Optional<TCC> findById(Long id);

    Optional<TCC> findByComentario(String comentario);
}
