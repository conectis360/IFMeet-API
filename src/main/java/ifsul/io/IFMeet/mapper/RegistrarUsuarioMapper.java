package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.RegistrarUsuarioDTO;
import ifsul.io.IFMeet.controller.dto.UsuarioDTO;
import ifsul.io.IFMeet.domain.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface RegistrarUsuarioMapper extends BeanMapper<Usuario, RegistrarUsuarioDTO>{

    @Override
    @InheritInverseConfiguration
    Usuario toEntity(RegistrarUsuarioDTO registrarUsuarioDTO);

    @Override
    @Mapping(source = "id", target = "id")
    RegistrarUsuarioDTO toDto(Usuario usuario);

}
