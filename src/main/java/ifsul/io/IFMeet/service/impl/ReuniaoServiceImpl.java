package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.domain.Usuario;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.repository.ReuniaoRepository;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.service.ReuniaoService;
import ifsul.io.IFMeet.service.UsuarioService;
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
    UsuarioService usuarioService;

    @Autowired
    Messages messages;

    public Optional<Reuniao> findById(Long id) {
        return reuniaoRepository.findById(id);
    }

    public List<Reuniao> findAll() {
        return null;
    }

    public List<Reuniao> findAllByOrientador() {
        log.debug("into findAllByOrientador method");
        Usuario orientador = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado"))));
        List<Reuniao> listaTrabalhos = reuniaoRepository.findAllByOrientadorId(orientador.getId());
        return listaTrabalhos;
    }

    public void save(Reuniao reuniao) {
        reuniaoRepository.save(reuniao);
    }

}
