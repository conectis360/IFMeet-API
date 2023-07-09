package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.repository.CursoRepository;
import ifsul.io.IFMeet.repository.ReuniaoRepository;
import ifsul.io.IFMeet.service.ReuniaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReuniaoServiceImpl implements ReuniaoService {

    @Autowired
    ReuniaoRepository reuniaoRepository;

    @Autowired
    Messages messages;

    public Optional<Reuniao> findById(Long id) {
        return reuniaoRepository.findById(id);
    }

    public List<Reuniao> findAll() {
        return null;
    }

    public void save(Reuniao reuniao) {
    }

}
