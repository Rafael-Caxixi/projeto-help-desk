package com.rafael.helpdesk.domain.chamado;

import com.rafael.helpdesk.domain.cliente.Cliente;

public record DtoListagemChamadosAberto(Long idChamado, Categoria categoria, String descricao) {

    public DtoListagemChamadosAberto(Chamado chamado){
        this(chamado.getId(), chamado.getCategoria(), chamado.getDescricao());
    }


}
