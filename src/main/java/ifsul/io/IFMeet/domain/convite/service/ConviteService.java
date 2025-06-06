package ifsul.io.IFMeet.domain.convite.service;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.convite.model.Convite;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.domain.convite.repository.ConviteRepository;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioRepository;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.domain.emails.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConviteService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ConviteRepository conviteRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    Messages messages;

    SecurityUtils securityUtils;

    public void enviarConvite(Convite convite) {
        log.debug("entrou enviarConvite");
        if (this.checarEmailCadastrado(convite.getEmailAluno())) {
            throw new BusinessException(messages.get("convite.email-ja-cadastrado"));
        }
        convite.setCodigoSecreto(geraCodigoSecretoEmail());
        emailService.enviarEmailDeCadastro(convite);
        conviteRepository.save(convite);
    }

    public Convite findCredenciais(String codigo) {
        Convite convite = conviteRepository.findByCodigoSecreto(codigo);
        if (convite == null) {
            throw new BusinessException(messages.get("convite.nao-encontrado"));
        }
        return convite;
    }

    private String geraCodigoSecretoEmail() {
        int tamanho = 40;
        boolean usarLetras = true;
        boolean usarNumeros = false;
        return RandomStringUtils.random(tamanho, usarLetras, usarNumeros);
    }

    private Boolean checarEmailCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
