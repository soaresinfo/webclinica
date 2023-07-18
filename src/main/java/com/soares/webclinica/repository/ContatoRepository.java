package com.soares.webclinica.repository;

import com.soares.webclinica.repository.model.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, String> {
    Optional<ContatoEntity> findByContato(String contato);
}
