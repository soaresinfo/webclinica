package com.soares.webclinica.repository;

import com.soares.webclinica.repository.model.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, UUID> {
    Optional<PacienteEntity> findByCpf(String cpf);

    Optional<PacienteEntity> findByNomePaciente(String nomePaciente);
}
