package com.rafael.helpdesk.domain.chamado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoEnvioEmail(@NotNull Long idChamado, @NotNull Long idConsultor, @NotBlank String resposta) {
}
