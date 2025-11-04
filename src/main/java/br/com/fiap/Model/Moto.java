package br.com.fiap.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_moto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Placa é obrigatória")
    @Size(min = 7, max = 7, message = "Placa deve ter 7 caracteres")
    @Column(unique = true, nullable = false)
    private String placa;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "Status é obrigatório")
    private String status; // Ex: "No pátio", "Em movimento", "Ausente"
}
