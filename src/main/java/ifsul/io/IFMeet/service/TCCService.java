package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.TCC;

import java.util.List;
import java.util.Optional;

public interface TCCService {
    List<TCC> findAll();

    Optional<TCC> findById(Long id);

    Optional<TCC> findByComentario(String comentario);
}
