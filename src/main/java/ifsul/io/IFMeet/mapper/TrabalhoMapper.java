package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.TrabalhoDTO;
import ifsul.io.IFMeet.domain.Trabalho;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface TrabalhoMapper extends BeanMapper<Trabalho, TrabalhoDTO>{

    @Override
    @InheritInverseConfiguration
    Trabalho toEntity(TrabalhoDTO trabalhoDTO);

    @Override
    @Mapping(source = "id", target = "codigoTrabalho")
    @Mapping(source = "curso.id", target = "curso.codigoCurso")
    TrabalhoDTO toDto(Trabalho trabalho);

}
