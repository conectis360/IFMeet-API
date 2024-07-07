package ifsul.io.IFMeet.mapper;

import ifsul.io.IFMeet.controller.dto.DocumentacaoDTO;
import ifsul.io.IFMeet.controller.dto.TarefaDTO;
import ifsul.io.IFMeet.domain.Documentacao;
import ifsul.io.IFMeet.domain.Tarefa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface DocumentacaoMapper extends BeanMapper<Documentacao, DocumentacaoDTO> {

    @Override
    @InheritInverseConfiguration
    Documentacao toEntity(DocumentacaoDTO documentacaoDTO);

    @Override
    @Mapping(source = "id", target = "codigoDocumentacao")
    @Mapping(source = "trabalho.id", target = "trabalho.codigoTrabalho")
    DocumentacaoDTO toDto(Documentacao documentacao);

}
