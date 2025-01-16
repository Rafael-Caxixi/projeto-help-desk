package com.rafael.helpdesk.service;

import com.rafael.helpdesk.domain.cliente.Cliente;
import com.rafael.helpdesk.domain.cliente.DtoAtualizacaoCliente;
import com.rafael.helpdesk.domain.cliente.DtoCadastroCliente;
import com.rafael.helpdesk.domain.cliente.DtoListagemCliente;
import com.rafael.helpdesk.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public ResponseEntity cadastrar(DtoCadastroCliente dto, UriComponentsBuilder uriBuilder) {
            if(repository.existsByCnpj(dto.cnpj())){
                return ResponseEntity.badRequest().body("CNPJ já cadastrado");
            }
            var cliente = new Cliente(dto);
            repository.save(cliente);

            var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(uri).body(new DtoListagemCliente(cliente));
    }

    public Page<DtoListagemCliente> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DtoListagemCliente::new);
    }

    public ResponseEntity atualizar(DtoAtualizacaoCliente dto) {
        var cliente = repository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        cliente.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoListagemCliente(cliente));
    }

    public ResponseEntity deletar(Long id) {
        var cliente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        repository.delete(cliente);
        return ResponseEntity.noContent().build();
    }


}
