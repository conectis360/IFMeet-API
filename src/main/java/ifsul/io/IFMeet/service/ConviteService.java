package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Convite;

public interface ConviteService {
    void enviarConvite(Convite convite);

    Convite findCredenciais(String codigo);
}
