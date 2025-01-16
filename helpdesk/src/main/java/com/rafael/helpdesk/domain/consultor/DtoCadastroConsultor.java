package com.rafael.helpdesk.domain.consultor;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record DtoCadastroConsultor(
        @NotBlank String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        String telefone) {
}
