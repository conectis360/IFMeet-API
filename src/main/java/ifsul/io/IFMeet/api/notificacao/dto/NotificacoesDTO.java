package ifsul.io.IFMeet.api.notificacao.dto;


import ifsul.io.IFMeet.domain.notificacao.model.QuantidadeNotificacoesDto;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificacoesDTO {

    private Long total;

    private List<QuantidadeNotificacoesDto> quantidadeNotificacoesDtoList;
}