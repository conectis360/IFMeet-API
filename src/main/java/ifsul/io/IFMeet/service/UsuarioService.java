package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Roles;
import ifsul.io.IFMeet.domain.Usuario;

import java.util.List;


public interface UsuarioService {
    void registrarOrientando(Usuario usuario);
    void registrarOrientador(Usuario usuario);
    void registrarAdmin(Usuario usuario);
    List<Roles> retornarAlunos();
    List<Roles> retornarOrientadores();
    Usuario retornarUsuarioLogado(String username);
}
