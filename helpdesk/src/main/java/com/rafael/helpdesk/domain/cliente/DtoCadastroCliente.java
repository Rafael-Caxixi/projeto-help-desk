package com.rafael.helpdesk.domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record DtoCadastroCliente(
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

