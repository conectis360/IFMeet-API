package ifsul.io.IFMeet.api.curso.mapper;

import ifsul.io.IFMeet.api.curso.dto.CursoDTO;
import ifsul.io.IFMeet.domain.curso.model.Curso;
import ifsul.io.IFMeet.mapper.BeanMapper;
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
