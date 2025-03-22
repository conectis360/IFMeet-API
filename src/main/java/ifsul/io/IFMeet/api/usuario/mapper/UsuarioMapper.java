package ifsul.io.IFMeet.api.usuario.mapper;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface UsuarioMapper extends BeanMapper<Usuario, UsuarioDTO>{

    @Override
    @InheritInverseConfiguration
    Usuario toEntity(UsuarioDTO usuarioDTO);

    @Override
    @Mapping(source = "id", target = "codigoUsuario")
    UsuarioDTO toDto(Usuario usuario);

}
