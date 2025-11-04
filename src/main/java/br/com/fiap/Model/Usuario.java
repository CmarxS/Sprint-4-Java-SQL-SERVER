package br.com.fiap.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String nome;

    @Column(nullable=false, unique=true, length=120)
    private String email;

    @Column(nullable=false)
    private String senha; // pode ter prefixo {noop} para demo

    @Column(nullable=false)
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}



