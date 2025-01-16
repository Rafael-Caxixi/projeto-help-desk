package com.rafael.helpdesk.domain.chamado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCadastroChamado(
        @NotNull
        Long idCliente,
        @NotNull
        Categoria categoria,
        @NotBlank
        String descricao
) {
}
