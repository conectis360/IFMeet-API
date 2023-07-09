package ifsul.io.IFMeet.repository;


import ifsul.io.IFMeet.Config.Role;
import ifsul.io.IFMeet.domain.TipoUsuario;
import ifsul.io.IFMeet.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsuario(String username);

    Boolean existsByUsuario(String username);

    Boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

    List<Usuario> findByTipoUsuarioTipoUsuario(Role role);


}
