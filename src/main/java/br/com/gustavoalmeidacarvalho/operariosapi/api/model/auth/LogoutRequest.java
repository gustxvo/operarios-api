package br.com.gustavoalmeidacarvalho.operariosapi.api.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record LogoutRequest(@JsonProperty("device_id") Long deviceId,
                            @JsonProperty("refresh_token") UUID refreshToken) {
}
