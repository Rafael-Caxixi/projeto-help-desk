package com.rafael.helpdesk.domain.cliente;

public record DtoListagemCliente(String nome, String email, String cnpj, String telefone) {

    public DtoListagemCliente(Cliente cliente){
        this(cliente.getNome(), cliente.getEmail(),cliente.getCnpj(), cliente.getTelefone());
    }

}
