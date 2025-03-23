package ifsul.io.IFMeet.api.notificacao.mapper;

import ifsul.io.IFMeet.api.notificacao.dto.NotificacaoViewDTO;
import ifsul.io.IFMeet.domain.notificacao.model.NotificacaoView;
import ifsul.io.IFMeet.mapper.BeanMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = BeanMapper.SPRING)
public interface NotificacaoViewMapper extends BeanMapper<NotificacaoView, NotificacaoViewDTO> {

    @Override
    @InheritInverseConfiguration
    NotificacaoView toEntity(NotificacaoViewDTO notificacaoDTO);

    @Override
    NotificacaoViewDTO toDto(NotificacaoView notificacao);

}
