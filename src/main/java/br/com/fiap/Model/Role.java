package br.com.fiap.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_role")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=40)
    private String nome; // ex: ADMIN, OPERADOR

    public Role(String nome) { this.nome = nome; }
}

