package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.AtaDTO;
import ifsul.io.IFMeet.domain.Ata;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface AtaMapper extends BeanMapper<Ata, AtaDTO> {

    @Override
    @InheritInverseConfiguration
    Ata toEntity(AtaDTO ataDTO);

    @Override
    @Mapping(source = "id", target = "codigoAta")
    AtaDTO toDto(Ata ata);

}
