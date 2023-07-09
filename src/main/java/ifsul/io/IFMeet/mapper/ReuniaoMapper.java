package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.AtaDTO;
import ifsul.io.IFMeet.controller.dto.ReuniaoDTO;
import ifsul.io.IFMeet.domain.Ata;
import ifsul.io.IFMeet.domain.Reuniao;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface ReuniaoMapper extends BeanMapper<Reuniao, ReuniaoDTO> {

    @Override
    @InheritInverseConfiguration
    Reuniao toEntity(ReuniaoDTO reuniaoDTO);

    @Override
    @Mapping(source = "id", target = "codigoReuniao")
    ReuniaoDTO toDto(Reuniao reuniao);

}
