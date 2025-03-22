package ifsul.io.IFMeet.domain.tcc.service;

import ifsul.io.IFMeet.domain.tcc.model.TCC;
import ifsul.io.IFMeet.domain.tcc.repository.TCCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TCCService {

    private final TCCRepository tccRepository;


    public List<TCC> findAll() {
        List<TCC> tcc = tccRepository.findAll();
        return tcc;
    }


    public Optional<TCC> findById(Long id) {
        Optional<TCC> tcc = tccRepository.findById(id);
        return tcc;
    }


    public Optional<TCC> findByComentario(String comentario) {
        Optional<TCC> tcc = tccRepository.findByComentario(comentario);
        return tcc;
    }
}
