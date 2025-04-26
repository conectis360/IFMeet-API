package ifsul.io.IFMeet.domain.notificacao.repository;

import ifsul.io.IFMeet.domain.notificacao.model.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TipoNotificacaoRepository extends JpaRepository<TipoNotificacao, Long>, JpaSpecificationExecutor<TipoNotificacao> {

}
