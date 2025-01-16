package com.rafael.helpdesk.domain.consultor;

import com.rafael.helpdesk.domain.cliente.Cliente;

public record DtoListagemConsultor(String nome, String email, String cpf, String telefone) {

    public DtoListagemConsultor(Consultor consultor){
        this(consultor.getNome(), consultor.getEmail(),consultor.getCpf(), consultor.getTelefone());
    }
}
