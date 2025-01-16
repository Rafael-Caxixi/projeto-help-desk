package com.rafael.helpdesk.domain.chamado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Chamado")
@Table(name = "chamados")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
    public class Chamado {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long idCliente;

        private Long idConsultor;

        @Enumerated(EnumType.STRING)
        private Categoria categoria;

        private String descricao;

        private Boolean aberto;

    public Chamado(DtoCadastroChamado dto){
        this.idCliente = dto.idCliente();
        this.idConsultor = null;
        this.categoria = dto.categoria();
        this.descricao = dto.descricao();
        this.aberto = true;
    }

    public void capturar(Long idConsultor){
        this.idConsultor = idConsultor;
        this.aberto = false;
    }

}
