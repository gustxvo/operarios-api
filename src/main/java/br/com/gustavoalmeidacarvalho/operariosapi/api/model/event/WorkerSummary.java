package br.com.gustavoalmeidacarvalho.operariosapi.api.model.event;

import br.com.gustavoalmeidacarvalho.operariosapi.domain.model.user.User;

import java.util.UUID;

public record WorkerSummary(UUID id, String name) {

    public static WorkerSummary fromEntity(User user) {
        return new WorkerSummary(user.getId(), user.getName());
    }
}