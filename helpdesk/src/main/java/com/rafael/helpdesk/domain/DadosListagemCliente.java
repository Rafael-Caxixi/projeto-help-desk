package com.rafael.helpdesk.domain;

public record DadosListagemCliente(String nome, String email, String cnpj, String telefone) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getNome(), cliente.getEmail(),cliente.getCnpj(), cliente.getTelefone());
    }

}
