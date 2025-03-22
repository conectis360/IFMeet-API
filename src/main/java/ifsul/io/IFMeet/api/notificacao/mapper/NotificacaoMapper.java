package ifsul.io.IFMeet.api.notificacao.mapper;

import ifsul.io.IFMeet.api.ata.dto.AtaDTO;
import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoDTO;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = BeanMapper.SPRING)
public interface NotificacaoMapper extends BeanMapper<Notificacao, NotificacaoDTO> {

    @Override
    @InheritInverseConfiguration
    Notificacao toEntity(NotificacaoDTO notificacaoDTO);

    @Override
    @Mapping(source = "id", target = "codigoNotificacao")
    NotificacaoDTO toDto(Notificacao notificacao);

}
