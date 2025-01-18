package com.rafael.helpdesk.repository;

import com.rafael.helpdesk.domain.chamado.Chamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    Page<Chamado> findAllByIdCliente(Long id, Pageable paginacao);

    Page<Chamado> findAllByAbertoTrue(Pageable paginacao);

    Chamado findAllByIdCliente(Long id);
}
