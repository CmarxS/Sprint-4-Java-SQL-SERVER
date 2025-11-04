package br.com.fiap.Repository;

import br.com.fiap.Model.HistoricoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoMovimentacaoRepository extends JpaRepository<HistoricoMovimentacao, Long> {
    List<HistoricoMovimentacao> findAllByMoto_Id(Long motoId);
    List<HistoricoMovimentacao> findAllByZona_Id(Long zonaId);
}
