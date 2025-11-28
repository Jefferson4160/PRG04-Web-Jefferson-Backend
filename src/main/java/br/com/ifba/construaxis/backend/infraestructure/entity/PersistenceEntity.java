package br.com.ifba.construaxis.backend.infraestructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class PersistenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Getters e Setters para o ID (adicionados via Lombok)
}
