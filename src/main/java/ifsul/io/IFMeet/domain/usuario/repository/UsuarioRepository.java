package ifsul.io.IFMeet.domain.usuario.repository;


import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>, JpaSpecificationExecutor<Usuario> {
    Optional<Usuario> findByUsuario(String username);

    Boolean existsByUsuario(String username);

    Boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

    List<Usuario> findByTipoUsuarioTipoUsuario(Role role);


}
