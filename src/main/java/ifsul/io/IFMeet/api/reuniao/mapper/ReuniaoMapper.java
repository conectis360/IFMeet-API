package ifsul.io.IFMeet.api.reuniao.mapper;

import ifsul.io.IFMeet.api.reuniao.dto.ReuniaoDTO;
import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.api.trabalho.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import ifsul.io.IFMeet.api.usuario.mapper.UsuarioMapper;
import ifsul.io.IFMeet.domain.reuniao.model.Reuniao;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING, uses = {
        UsuarioMapper.class,
        TrabalhoMapper.class
})
public interface ReuniaoMapper extends BeanMapper<Reuniao, ReuniaoDTO> {

    @Override
    @InheritInverseConfiguration
    Reuniao toEntity(ReuniaoDTO reuniaoDTO);

    @Override
    @Mapping(source = "id", target = "codigoReuniao")
    ReuniaoDTO toDto(Reuniao reuniao);

}
