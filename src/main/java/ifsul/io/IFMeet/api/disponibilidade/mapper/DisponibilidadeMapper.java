package ifsul.io.IFMeet.api.disponibilidade.mapper;

import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeDTO;
import ifsul.io.IFMeet.api.usuario.mapper.UsuarioMapper;
import ifsul.io.IFMeet.domain.disponibilidade.model.Disponibilidade;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING, uses = {
        UsuarioMapper.class,
})
public interface DisponibilidadeMapper extends BeanMapper<Disponibilidade, DisponibilidadeDTO> {

    @Override
    @InheritInverseConfiguration
    Disponibilidade toEntity(DisponibilidadeDTO reuniaoDTO);

    @Override
    DisponibilidadeDTO toDto(Disponibilidade disponibilidade);

}
