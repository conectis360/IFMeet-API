package ifsul.io.IFMeet.domain.notificacao.model;

/**
 * Enum que representa os tipos de notificação existentes no sistema
 * com seus respectivos IDs no banco de dados
 */
public enum TipoNotificacaoEnum {
    REUNIAO(1L),
    ATA(2L),
    TAREFA(3L);

    private final Long id;

    TipoNotificacaoEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
