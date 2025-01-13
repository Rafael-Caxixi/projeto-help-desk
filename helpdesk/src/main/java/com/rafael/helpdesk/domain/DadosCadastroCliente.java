package com.rafael.helpdesk.domain;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String cnpj,
        @NotBlank
        String telefone) {


}

