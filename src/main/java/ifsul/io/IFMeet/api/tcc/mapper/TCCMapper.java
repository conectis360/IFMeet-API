package ifsul.io.IFMeet.api.tcc.mapper;

import ifsul.io.IFMeet.api.tcc.dto.TCCDTO;
import ifsul.io.IFMeet.domain.tcc.model.TCC;
import ifsul.io.IFMeet.mapper.BeanMapper;
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
