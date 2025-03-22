package ifsul.io.IFMeet.api.trabalho.mapper;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.api.usuario.mapper.UsuarioMapper;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING, uses = {
        UsuarioMapper.class
})
public interface TrabalhoMapper extends BeanMapper<Trabalho, TrabalhoDTO> {

    @Override
    @InheritInverseConfiguration
    Trabalho toEntity(TrabalhoDTO trabalhoDTO);

    @Override
    @Mapping(source = "id", target = "codigoTrabalho")
    @Mapping(source = "curso.id", target = "curso.codigoCurso")
    @Mapping(source = "aluno.id", target = "aluno.codigoUsuario")
    @Mapping(source = "orientador.id", target = "orientador.codigoUsuario")
    TrabalhoDTO toDto(Trabalho trabalho);

}
