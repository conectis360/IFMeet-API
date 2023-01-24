package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.CursoDTO;
import ifsul.io.IFMeet.domain.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface CursoMapper extends BeanMapper<Curso, CursoDTO> {

    @Override
    @InheritInverseConfiguration
    Curso toEntity(CursoDTO cursoDTO);

    @Override
    @Mapping(source = "id", target = "codigoCurso")
    CursoDTO toDto(Curso curso);

}
