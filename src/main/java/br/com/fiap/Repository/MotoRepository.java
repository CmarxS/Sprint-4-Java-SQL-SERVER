package br.com.fiap.Repository;

import br.com.fiap.Model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
