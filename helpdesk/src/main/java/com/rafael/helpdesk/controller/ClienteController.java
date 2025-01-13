package com.rafael.helpdesk.controller;

import com.rafael.helpdesk.domain.Cliente;
import com.rafael.helpdesk.domain.DadosCadastroCliente;
import com.rafael.helpdesk.domain.DadosListagemCliente;
import com.rafael.helpdesk.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados){
        repository.save(new Cliente(dados));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemCliente::new);
        //var page = repository.listarTudo();
        return ResponseEntity.ok(page);
    }

}
