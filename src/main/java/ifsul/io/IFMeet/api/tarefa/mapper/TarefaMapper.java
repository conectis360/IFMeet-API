package ifsul.io.IFMeet.api.tarefa.mapper;

import ifsul.io.IFMeet.api.tarefa.dto.TarefaDTO;
import ifsul.io.IFMeet.api.trabalho.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.api.usuario.mapper.UsuarioMapper;
import ifsul.io.IFMeet.domain.tarefa.model.Tarefa;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING, uses = {
        UsuarioMapper.class,
        TrabalhoMapper.class
})
public interface TarefaMapper extends BeanMapper<Tarefa, TarefaDTO> {

    @Override
    @InheritInverseConfiguration
    Tarefa toEntity(TarefaDTO tarefaDTO);

    @Override
    @Mapping(source = "id", target = "codigoTarefa")
    TarefaDTO toDto(Tarefa tarefa);

}
