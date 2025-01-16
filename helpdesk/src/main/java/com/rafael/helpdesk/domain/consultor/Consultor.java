package com.rafael.helpdesk.domain.consultor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Consultor")
@Table(name = "consultores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Consultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String telefone;

    public Consultor(DtoCadastroConsultor dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
    }

    public void atualizarInformacoes(DtoAtualizacaoConsultor dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.email() != null){
            this.email = dto.email();
        }
        if(dto.cpf() != null){
            this.cpf = dto.cpf();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
    }
}
