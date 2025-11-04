package br.com.fiap.Service;

import br.com.fiap.Model.ZonaVirtual;
import br.com.fiap.Repository.ZonaVirtualRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaVirtualService {

    private final ZonaVirtualRepository repository;

    public ZonaVirtualService(ZonaVirtualRepository repository) {
        this.repository = repository;
    }

    public List<ZonaVirtual> listar() {
        return repository.findAll();
    }

    public ZonaVirtual buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zona Virtual não encontrada: " + id));
    }

    @Transactional
    public ZonaVirtual salvar(ZonaVirtual zona) {
        // Se já existir outra zona com o mesmo nome (case-insensitive) e ID diferente, barrar
        ZonaVirtual existenteMesmoNome = repository.findByNomeIgnoreCase(zona.getNome());
        if (existenteMesmoNome != null && (zona.getId() == null || !existenteMesmoNome.getId().equals(zona.getId()))) {
            throw new IllegalArgumentException("Já existe uma zona chamada '" + zona.getNome() + "'.");
        }
        return repository.save(zona);
    }

    @Transactional
    public ZonaVirtual atualizar(Long id, ZonaVirtual dados) {
        ZonaVirtual existente = buscarPorId(id);
        // Verifica duplicidade por nome (ignorando a própria)
        ZonaVirtual outroComMesmoNome = repository.findByNomeIgnoreCase(dados.getNome());
        if (outroComMesmoNome != null && !outroComMesmoNome.getId().equals(id)) {
            throw new IllegalArgumentException("Já existe uma zona chamada '" + dados.getNome() + "'.");
        }
        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        existente.setTipo(dados.getTipo());
        return repository.save(existente);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Zona Virtual não encontrada: " + id);
        }
        repository.deleteById(id);
    }
}

