package com.rafael.helpdesk.repository;

import com.rafael.helpdesk.domain.consultor.Consultor;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultorRepository extends JpaRepository<Consultor, Long> {

    Boolean existsByCpf(@NotBlank String cpf);

}
