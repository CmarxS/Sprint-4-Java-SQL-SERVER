package br.com.fiap.Repository;

import br.com.fiap.Model.ZonaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonaVirtualRepository extends JpaRepository<ZonaVirtual, Long> {
    ZonaVirtual findByNomeIgnoreCase(String nome); // retorna null se não encontrar
    boolean existsByNomeIgnoreCase(String nome);
}
