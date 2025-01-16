package com.rafael.helpdesk.repository;

import com.rafael.helpdesk.domain.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Boolean existsByCnpj(@NotBlank String cnpj);
}
