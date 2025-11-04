package br.com.fiap.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_zona_virtual")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaVirtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da zona é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;

    // Exemplo: Pátio A, Zona de Segurança, Estacionamento Visitantes
    private String tipo;
}

