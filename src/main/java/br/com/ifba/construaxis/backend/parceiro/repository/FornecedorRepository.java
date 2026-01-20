package br.com.ifba.construaxis.backend.parceiro.repository;

import br.com.ifba.construaxis.backend.parceiro.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {
    // Busca para evitar duplicidade de CNPJ no banco
    Optional<Fornecedor> findByCnpj(String cnpj);
}