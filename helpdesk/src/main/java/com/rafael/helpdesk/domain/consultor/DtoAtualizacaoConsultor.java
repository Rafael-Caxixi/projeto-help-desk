package com.rafael.helpdesk.domain.consultor;

import jakarta.validation.constraints.NotNull;

public record DtoAtualizacaoConsultor(
        @NotNull
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone) {

}
