package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.ConviteDTO;
import ifsul.io.IFMeet.domain.Convite;
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
