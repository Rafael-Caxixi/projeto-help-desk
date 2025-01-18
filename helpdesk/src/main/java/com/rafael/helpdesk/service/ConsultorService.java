package com.rafael.helpdesk.service;

import com.rafael.helpdesk.domain.chamado.*;
import com.rafael.helpdesk.domain.cliente.Cliente;
import com.rafael.helpdesk.domain.consultor.Consultor;
import com.rafael.helpdesk.domain.consultor.DtoAtualizacaoConsultor;
import com.rafael.helpdesk.domain.consultor.DtoCadastroConsultor;
import com.rafael.helpdesk.domain.consultor.DtoListagemConsultor;
import com.rafael.helpdesk.repository.ChamadoRepository;
import com.rafael.helpdesk.repository.ClienteRepository;
import com.rafael.helpdesk.repository.ConsultorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.Properties;

@Service
public class ConsultorService {

    @Autowired
    ConsultorRepository consultorRepository;
    
    @Autowired
    ChamadoRepository chamadoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnvioEmailService envioEmailService;

    public ResponseEntity cadastrar(DtoCadastroConsultor dto, UriComponentsBuilder uriBuilder) {
        if(consultorRepository.existsByCpf(dto.cpf())){
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }
        var consultor = new Consultor(dto);
        consultorRepository.save(consultor);

        var uri = uriBuilder.path("/consultores/{id}").buildAndExpand(consultor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoListagemConsultor(consultor));
    }

    public Page<DtoListagemConsultor> listar(Pageable paginacao) {
        return consultorRepository.findAll(paginacao).map(DtoListagemConsultor::new);
    }

    public ResponseEntity atualizar(DtoAtualizacaoConsultor dto) {
        var consultor = consultorRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Consultor não encontrado"));
        consultor.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoListagemConsultor(consultor));
    }

    public ResponseEntity deletar(Long id) {
        var consultor = consultorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultor não encontrado"));
        consultorRepository.delete(consultor);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity detalharPorId(Long id) {
        var consultor = consultorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return ResponseEntity.ok().body(new DtoListagemConsultor(consultor));
    }

    public ResponseEntity capturarChamado(DtoCapturaChamado dto) {
        Chamado chamado = chamadoRepository.findById(dto.idChamado()).orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));
        if(chamado.getIdConsultor() != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Chamado ja foi capturado");
        }
        if(!consultorRepository.existsById(dto.idConsultor())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consultor não existe");
        }
        chamado.capturar(dto.idConsultor());
        chamadoRepository.save(chamado);
        return ResponseEntity.ok().body("Chamado capturado com id: " + chamado.getId());

    }

    public Page<DtoListagemChamadosAberto> listarChamadosAberto(Pageable paginacao) {
        return chamadoRepository.findAllByAbertoTrue(paginacao).map(chamado -> new DtoListagemChamadosAberto(chamado));
    }

    public ResponseEntity enviarEmail(DtoEnvioEmail dto) {
        Chamado chamado = chamadoRepository.findById(dto.idChamado()).orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));
        Consultor consultor = consultorRepository.findById(dto.idConsultor()).orElseThrow(() -> new EntityNotFoundException("Consultor não encontrado"));
        Cliente cliente = clienteRepository.findById(chamado.getIdCliente()).orElseThrow(() -> new EntityNotFoundException("Email do cliente não encontrado"));

        if(chamado.getIdConsultor() != consultor.getId()){
            return ResponseEntity.badRequest().body("O chamado não pertence ao consultor informado");
        }
        envioEmailService.enviarEmailTexto(cliente.getEmail(), "Chamado " + chamado.getId(), "Seu chamado " + chamado.getId() + " foi respondido");
        return ResponseEntity.ok().body("Email enviado com sucesso");
    }
}
