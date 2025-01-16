package com.rafael.helpdesk.service;

import com.rafael.helpdesk.domain.cliente.Cliente;
import com.rafael.helpdesk.domain.cliente.DtoListagemCliente;
import com.rafael.helpdesk.domain.consultor.Consultor;
import com.rafael.helpdesk.domain.consultor.DtoAtualizacaoConsultor;
import com.rafael.helpdesk.domain.consultor.DtoCadastroConsultor;
import com.rafael.helpdesk.domain.consultor.DtoListagemConsultor;
import com.rafael.helpdesk.repository.ConsultorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ConsultorService {

    @Autowired
    ConsultorRepository repository;

    public ResponseEntity cadastrar(DtoCadastroConsultor dto, UriComponentsBuilder uriBuilder) {
        if(repository.existsByCpf(dto.cpf())){
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }
        var consultor = new Consultor(dto);
        repository.save(consultor);

        var uri = uriBuilder.path("/consultores/{id}").buildAndExpand(consultor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoListagemConsultor(consultor));
    }

    public Page<DtoListagemConsultor> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DtoListagemConsultor::new);
    }

    public ResponseEntity atualizar(DtoAtualizacaoConsultor dto) {
        var consultor = repository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Consultor não encontrado"));
        consultor.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoListagemConsultor(consultor));
    }

    public ResponseEntity deletar(Long id) {
        var consultor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultor não encontrado"));
        repository.delete(consultor);
        return ResponseEntity.noContent().build();
    }
}
