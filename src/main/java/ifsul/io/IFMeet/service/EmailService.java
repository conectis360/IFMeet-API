package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Convite;
import ifsul.io.IFMeet.domain.EmailDetails;

public interface EmailService {

    void enviarEmailSimples(EmailDetails details);
    void enviarEmailComAnexo(EmailDetails details);
    void enviarEmailDeCadastro(Convite convite);
}
