package ifsul.io.IFMeet.api.ata.mapper;

import ifsul.io.IFMeet.api.ata.dto.AtaDTO;
import ifsul.io.IFMeet.api.calendarevent.mapper.CalendarEventMapper;
import ifsul.io.IFMeet.api.trabalho.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING, uses = {
        TrabalhoMapper.class,
        CalendarEventMapper.class,
})
public interface AtaMapper extends BeanMapper<Ata, AtaDTO> {

    @Override
    @InheritInverseConfiguration
    Ata toEntity(AtaDTO ataDTO);

    @Override
    @Mapping(source = "id", target = "codigoAta")
    AtaDTO toDto(Ata ata);

}
