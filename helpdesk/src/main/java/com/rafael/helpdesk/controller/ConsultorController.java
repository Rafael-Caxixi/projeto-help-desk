package com.rafael.helpdesk.controller;

import com.rafael.helpdesk.domain.chamado.DtoCapturaChamado;
import com.rafael.helpdesk.domain.chamado.DtoListagemChamadosAberto;
import com.rafael.helpdesk.domain.consultor.DtoAtualizacaoConsultor;
import com.rafael.helpdesk.domain.consultor.DtoCadastroConsultor;
import com.rafael.helpdesk.domain.consultor.DtoListagemConsultor;
import com.rafael.helpdesk.service.ConsultorService;
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
@RequestMapping("/consultores")
public class ConsultorController {

    @Autowired
    ConsultorService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroConsultor dto, UriComponentsBuilder uriBuilder){
        return service.cadastrar(dto,uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DtoListagemConsultor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizacaoConsultor dto){
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
    public ResponseEntity capturarChamado(@RequestBody @Valid DtoCapturaChamado dto){
        return service.capturarChamado(dto);
    }

    @GetMapping("/chamados")
    public Page<DtoListagemChamadosAberto> listarChamadosAberto(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return service.listarChamadosAberto(paginacao);
    }


}
