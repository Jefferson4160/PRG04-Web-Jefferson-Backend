package br.com.ifba.construaxis.backend.estoque.repository;


import br.com.ifba.construaxis.backend.estoque.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}