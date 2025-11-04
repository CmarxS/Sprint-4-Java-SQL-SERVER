package br.com.fiap.Service;

import br.com.fiap.Model.HistoricoMovimentacao;
import br.com.fiap.Repository.HistoricoMovimentacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoMovimentacaoService {

    private final HistoricoMovimentacaoRepository repository;

    public HistoricoMovimentacaoService(HistoricoMovimentacaoRepository repository) {
        this.repository = repository;
    }

    public List<HistoricoMovimentacao> listar() {
        return repository.findAll();
    }

    public List<HistoricoMovimentacao> listarPorMoto(Long motoId) {
        return repository.findAllByMoto_Id(motoId);
    }

    public List<HistoricoMovimentacao> listarPorZona(Long zonaId) {
        return repository.findAllByZona_Id(zonaId);
    }

    public HistoricoMovimentacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Histórico não encontrado: " + id));
    }

    @Transactional
    public HistoricoMovimentacao salvar(HistoricoMovimentacao historico) {
        if (historico.getDataHora() == null) {
            historico.setDataHora(LocalDateTime.now());
        }
        return repository.save(historico);
    }

    @Transactional
    public HistoricoMovimentacao atualizar(Long id, HistoricoMovimentacao h) {
        HistoricoMovimentacao db = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Histórico não encontrado: " + id));
        db.setMoto(h.getMoto());
        db.setZona(h.getZona());
        db.setDataHora(h.getDataHora());
        db.setObservacao(h.getObservacao());
        return repository.save(db);
    }


    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Histórico não encontrado: " + id);
        }
        repository.deleteById(id);
    }
}

