package com.rafael.helpdesk.service;

import com.rafael.helpdesk.domain.chamado.Chamado;
import com.rafael.helpdesk.domain.chamado.DtoCadastroChamado;
import com.rafael.helpdesk.domain.chamado.DtoListagemChamado;
import com.rafael.helpdesk.domain.cliente.Cliente;
import com.rafael.helpdesk.domain.cliente.DtoAtualizacaoCliente;
import com.rafael.helpdesk.domain.cliente.DtoCadastroCliente;
import com.rafael.helpdesk.domain.cliente.DtoListagemCliente;
import com.rafael.helpdesk.repository.ChamadoRepository;
import com.rafael.helpdesk.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ChamadoRepository chamadoRepository;

    public ResponseEntity cadastrar(DtoCadastroCliente dto, UriComponentsBuilder uriBuilder) {
            if(clienteRepository.existsByCnpj(dto.cnpj())){
                return ResponseEntity.badRequest().body("CNPJ já cadastrado");
            }
            var cliente = new Cliente(dto);
            clienteRepository.save(cliente);

            var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(uri).body(new DtoListagemCliente(cliente));
    }

    public Page<DtoListagemCliente> listar(Pageable paginacao) {
        return clienteRepository.findAll(paginacao).map(DtoListagemCliente::new);
    }

    public ResponseEntity atualizar(DtoAtualizacaoCliente dto) {
        var cliente = clienteRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        cliente.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoListagemCliente(cliente));
    }

    public ResponseEntity deletar(Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity detalharPorId(Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return ResponseEntity.ok().body(new DtoListagemCliente(cliente));
    }

    public ResponseEntity abrirChamado(DtoCadastroChamado dto, UriComponentsBuilder uriBuilder) {

        var chamado = new Chamado(dto);
        chamadoRepository.save(chamado);


        Cliente cliente = clienteRepository.findById(chamado.getIdCliente()).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        var uri = uriBuilder.path("/clientes/suporte/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoListagemChamado(chamado, cliente));
    }
}
