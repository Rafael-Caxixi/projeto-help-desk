package com.rafael.helpdesk.domain.chamado;

public record DtoListagemChamadosPorCliente(Long id, Categoria categoria, String descricao, Boolean aberto) {

    public DtoListagemChamadosPorCliente(Chamado chamado){
        this(chamado.getId(), chamado.getCategoria(), chamado.getDescricao(), chamado.getAberto());
    }

}
