package ifsul.io.IFMeet.repository;


import ifsul.io.IFMeet.Config.Role;
import ifsul.io.IFMeet.domain.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Long> {
    Optional<TipoUsuario> findByTipoUsuario(Role name);
}
