package com.rafael.helpdesk.controller;

import com.rafael.helpdesk.domain.chamado.DtoCadastroChamado;
import com.rafael.helpdesk.domain.chamado.DtoListagemChamadosPorCliente;
import com.rafael.helpdesk.domain.cliente.DtoAtualizacaoCliente;
import com.rafael.helpdesk.domain.cliente.DtoCadastroCliente;
import com.rafael.helpdesk.domain.cliente.DtoListagemCliente;
import com.rafael.helpdesk.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping()
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroCliente dto, UriComponentsBuilder uriBuilder){
        return service.cadastrar(dto, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DtoListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizacaoCliente dto) {
        return service.atualizar(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        return service.deletar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPorId(@PathVariable Long id){
        return service.detalharPorId(id);
    }

    @PostMapping("/suporte")
    @Transactional
    public ResponseEntity abrirChamado(@RequestBody @Valid DtoCadastroChamado dto, UriComponentsBuilder uriBuilder){
        return service.abrirChamado(dto, uriBuilder);
    }

    @GetMapping("/chamados/{id}")
    public Page<DtoListagemChamadosPorCliente> listarMeusChamados(@PathVariable Long id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return service.listarMeusChamados(id, paginacao);
    }


}
