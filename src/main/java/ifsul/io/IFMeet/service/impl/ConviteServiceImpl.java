package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Convite;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.repository.ConviteRepository;
import ifsul.io.IFMeet.repository.UsuarioRepository;
import ifsul.io.IFMeet.service.ConviteService;
import ifsul.io.IFMeet.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConviteServiceImpl implements ConviteService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ConviteRepository conviteRepository;
    @Autowired
    EmailService emailService;

    @Autowired
    Messages messages;

    public void enviarConvite(Convite convite) {
        log.debug("entrou enviarConvite");
            if(this.checarEmailCadastrado(convite.getEmailAluno())) {
                throw new BusinessException(messages.get("convite.email-ja-cadastrado"));
            }
            convite.setCodigoSecreto(geraCodigoSecretoEmail());
            emailService.enviarEmailDeCadastro(convite);
            conviteRepository.save(convite);
    }

    public Convite findCredenciais(String codigo) {
        return conviteRepository.findByCodigoSecreto(codigo);
    }

    private String geraCodigoSecretoEmail () {
        int tamanho = 40;
        boolean usarLetras = true;
        boolean usarNumeros = false;
        return RandomStringUtils.random(tamanho, usarLetras, usarNumeros);
    }

    private Boolean checarEmailCadastrado (String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
