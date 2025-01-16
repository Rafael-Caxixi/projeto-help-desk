package com.rafael.helpdesk.domain.cliente;

import jakarta.validation.constraints.NotNull;

public record DtoAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        String email,
        String cnpj,
        String telefone
        ) {
}
