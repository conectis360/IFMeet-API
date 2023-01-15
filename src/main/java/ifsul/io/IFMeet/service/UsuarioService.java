package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Usuario;


public interface UsuarioService {
    void registrarOrientando(Usuario usuario);
    void registrarOrientador(Usuario usuario);
    void registrarAdmin(Usuario usuario);
}
