package ifsul.io.IFMeet.domain.notificacao.repository;

import ifsul.io.IFMeet.domain.notificacao.model.NotificacaoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificacaoViewRepository extends JpaRepository<NotificacaoView, Long>, JpaSpecificationExecutor<NotificacaoView> {
}
