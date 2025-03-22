package ifsul.io.IFMeet.api.status.mapper;

import ifsul.io.IFMeet.api.status.dto.StatusDTO;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.mapper.BeanMapper;
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
