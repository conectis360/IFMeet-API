package ifsul.io.IFMeet.api.usuario.mapper;

import ifsul.io.IFMeet.api.convite.dto.ConviteDTO;
import ifsul.io.IFMeet.domain.convite.model.Convite;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface ConviteMapper extends BeanMapper<Convite, ConviteDTO>{

    @Override
    @InheritInverseConfiguration
    Convite toEntity(ConviteDTO conviteDTO);

    @Override
    @Mapping(source = "codigoConvite", target = "codigoConvite")
    ConviteDTO toDto(Convite convite);

}
