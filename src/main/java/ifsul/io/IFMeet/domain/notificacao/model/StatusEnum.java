package ifsul.io.IFMeet.domain.notificacao.model;

/**
 * Enum que representa os tipos de status existentes no sistema
 * com seus respectivos IDs no banco de dados
 */
public enum StatusEnum {
    AGUARDANDO(0L),
    ACEITA(2L),
    CANCELADA(2L),
    REALIZADA(3L);

    private final Long id;

    StatusEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
