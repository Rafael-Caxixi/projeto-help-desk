package com.rafael.helpdesk.domain.chamado;

import jakarta.validation.constraints.NotNull;

public record DtoCapturaChamado(
        @NotNull
        Long idConsultor,
        @NotNull
        Long idChamado
) {
}
