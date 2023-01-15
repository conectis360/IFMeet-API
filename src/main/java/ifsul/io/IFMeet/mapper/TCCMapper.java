package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.TCCDTO;
import ifsul.io.IFMeet.domain.TCC;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface TCCMapper extends BeanMapper<TCC, TCCDTO>{

    @Override
    @InheritInverseConfiguration
    TCC toEntity(TCCDTO tccdto);

    @Override
    @Mapping(source = "id", target = "codigoTCC")
    TCCDTO toDto(TCC tcc);

}
