package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.domain.TCC;
import ifsul.io.IFMeet.repository.TCCRepository;
import ifsul.io.IFMeet.service.TCCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TCCServiceimpl implements TCCService {

    private final TCCRepository tccRepository;

    @Override
    public List<TCC> findAll() {
        List<TCC> tcc = tccRepository.findAll();
        return tcc;
    }

    @Override
    public Optional<TCC> findById(Long id) {
        Optional<TCC> tcc = tccRepository.findById(id);
        return tcc;
    }

    @Override
    public Optional<TCC> findByComentario(String comentario) {
        Optional<TCC> tcc = tccRepository.findByComentario(comentario);
        return tcc;
    }
}
