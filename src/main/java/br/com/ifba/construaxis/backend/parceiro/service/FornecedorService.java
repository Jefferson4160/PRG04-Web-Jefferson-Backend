package br.com.ifba.construaxis.backend.parceiro.service;

import br.com.ifba.construaxis.backend.cnpj.dto.CnpjResponseDTO;
import br.com.ifba.construaxis.backend.cnpj.service.CnpjService;
import br.com.ifba.construaxis.backend.parceiro.entity.Fornecedor;
import br.com.ifba.construaxis.backend.parceiro.repository.FornecedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final CnpjService cnpjService;

    public FornecedorService(FornecedorRepository fornecedorRepository, CnpjService cnpjService) {
        this.fornecedorRepository = fornecedorRepository;
        this.cnpjService = cnpjService;
    }

    @Transactional
    public Fornecedor cadastrarPorCnpj(String cnpj) {
        // 1. Limpa o CNPJ
        String cnpjLimpo = cnpj.replaceAll("\\D", "");

        // 2. Verifica se já existe no banco (Evita erro de Unique Constraint do SQL)
        if (fornecedorRepository.findByCnpj(cnpjLimpo).isPresent()) {
            throw new RuntimeException("Fornecedor já cadastrado com este CNPJ.");
        }

        // 3. Consulta a API externa (Usa o serviço que você já validou)
        CnpjResponseDTO dadosExternos = cnpjService.consultarCnpj(cnpjLimpo);

        // 4. Cria a entidade com os dados retornados
        Fornecedor novoFornecedor = new Fornecedor();
        novoFornecedor.setCnpj(cnpjLimpo);
        
        // Se a API não retornar nome fantasia, usa a razão social (nome)
        String nomeParaSalvar = (dadosExternos.getFantasia() != null && !dadosExternos.getFantasia().isBlank()) 
                                ? dadosExternos.getFantasia() 
                                : dadosExternos.getNome();
        
        novoFornecedor.setNomeFantasia(nomeParaSalvar);

        // 5. Salva no Supabase
        return fornecedorRepository.save(novoFornecedor);
    }

    public List<Fornecedor> findAll() {
    // Chama o repositório que faz o select * no Supabase
    return fornecedorRepository.findAll(); 
}


@Transactional
public Fornecedor save(Fornecedor fornecedor) {
    // 1. Limpa o CNPJ se houver antes de salvar
    if (fornecedor.getCnpj() != null) {
        fornecedor.setCnpj(fornecedor.getCnpj().replaceAll("\\D", ""));
    }
    // 2. Salva o objeto completo enviado pelo formulário
    return fornecedorRepository.save(fornecedor);
}

public void delete(UUID id) {
    // O JpaRepository já tem o deleteById por padrão
    fornecedorRepository.deleteById(id);
}
}