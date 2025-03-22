package ifsul.io.IFMeet.api.documentacao.mapper;

import ifsul.io.IFMeet.api.documentacao.dto.DocumentacaoDTO;
import ifsul.io.IFMeet.domain.documentacao.model.Documentacao;
import ifsul.io.IFMeet.mapper.BeanMapper;
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
    DocumentacaoDTO toDto(Documentacao documentacao);

}
