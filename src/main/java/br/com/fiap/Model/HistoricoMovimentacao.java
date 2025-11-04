package br.com.fiap.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historico_movimentacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A moto é obrigatória")
    @ManyToOne
    @JoinColumn(name = "moto_id", nullable = false)
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "zona_id")
    private ZonaVirtual zona;

    @NotNull(message = "Data e hora são obrigatórias")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // <- casa com input type="datetime-local"
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "observacao")
    private String observacao;
}
