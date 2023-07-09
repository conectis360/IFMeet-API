package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.ReuniaoDTO;
import ifsul.io.IFMeet.controller.dto.StatusDTO;
import ifsul.io.IFMeet.domain.Reuniao;
import ifsul.io.IFMeet.domain.Status;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface StatusMapper extends BeanMapper<Status, StatusDTO> {

    @Override
    @InheritInverseConfiguration
    Status toEntity(StatusDTO statusDTO);

    @Override
    @Mapping(source = "id", target = "codigoStatus")
    StatusDTO toDto(Status status);

}
