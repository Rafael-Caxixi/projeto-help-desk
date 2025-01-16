package com.rafael.helpdesk.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Cliente")
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String cnpj;

    private String telefone;


    public Cliente(DtoCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.cnpj = dados.cnpj();
        this.telefone = dados.telefone();
    }

    public void atualizarInformacoes(DtoAtualizacaoCliente dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.email() != null){
            this.email = dto.email();
        }
        if(dto.cnpj() != null){
            this.cnpj = dto.cnpj();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
    }
}