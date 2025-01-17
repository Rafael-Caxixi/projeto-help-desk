package com.rafael.helpdesk.domain.chamado;

import com.rafael.helpdesk.domain.cliente.Cliente;

public record DtoListagemChamados(Long idChamado, String nomeCliente, String descricao) {

    public DtoListagemChamados(Chamado chamado, Cliente cliente){
        this(chamado.getId(),cliente.getNome(), chamado.getDescricao());
    }


}
