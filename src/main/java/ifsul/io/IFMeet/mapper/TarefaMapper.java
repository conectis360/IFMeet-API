package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.TarefaDTO;
import ifsul.io.IFMeet.domain.Tarefa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface TarefaMapper extends BeanMapper<Tarefa, TarefaDTO> {

    @Override
    @InheritInverseConfiguration
    Tarefa toEntity(TarefaDTO tarefaDTO);

    @Override
    @Mapping(source = "id", target = "codigoTarefa")
    TarefaDTO toDto(Tarefa tarefa);

}
