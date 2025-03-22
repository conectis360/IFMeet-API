package ifsul.io.IFMeet.api.usuario.mapper;

import ifsul.io.IFMeet.api.usuario.dto.RegistrarUsuarioDTO;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.mapper.BeanMapper;
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
