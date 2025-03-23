package ifsul.io.IFMeet.domain.notificacao.repository;

import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.model.QuantidadeNotificacoesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>, JpaSpecificationExecutor<Notificacao> {
    @Query(
            value = "SELECT \n" +
                    "    tn.tipo_notificacao AS tipoNotificacao, \n" +
                    "    COUNT(n.id) AS quantidadeNotificacoes,\n" +
                    "    tn.icon AS icone\n" +
                    "FROM \n" +
                    "    public.notificacao n\n" +
                    "INNER JOIN \n" +
                    "    public.tipo_notificacao tn ON n.tipo_notificacao_id = tn.id\n" +
                    "INNER JOIN \n" +
                    "    public.usuario u ON n.usuario_id = u.id\n" +
                    "WHERE n.usuario_id = :usuario\n" +
                    "GROUP BY \n" +
                    "    u.nome, tn.tipo_notificacao, tn.icon\n" +
                    "ORDER BY \n" +
                    "    u.nome, tn.tipo_notificacao;",
            nativeQuery = true
    )
    List<QuantidadeNotificacoesDto> countNotificacoesPorUsuarioETipo(Long usuario);

    @Query(
            value = "SELECT\n" +
                    "    COUNT(n.id) AS totalNotificacoes\n" +
                    "FROM\n" +
                    "    public.notificacao n\n" +
                    "INNER JOIN\n" +
                    "    public.usuario u ON n.usuario_id = u.id\n" +
                    "WHERE n.usuario_id = :usuario\n" +
                    "GROUP BY\n" +
                    "    u.nome\n" +
                    "ORDER BY\n" +
                    "    u.nome;",
            nativeQuery = true
    )
    Long quantidadeDeNotificacoesPorUsuario(Long usuario);


}
