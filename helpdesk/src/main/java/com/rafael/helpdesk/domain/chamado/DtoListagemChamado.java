package com.rafael.helpdesk.domain.chamado;

import com.rafael.helpdesk.domain.cliente.Cliente;
import com.rafael.helpdesk.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public record DtoListagemChamado(Long idChamado, String nomeCliente, String descricao) {

    public DtoListagemChamado(Chamado chamado, Cliente cliente){

        this(chamado.getId(),cliente.getNome(), chamado.getDescricao());
    }


}
