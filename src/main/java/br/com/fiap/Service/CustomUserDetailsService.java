package br.com.fiap.Service;

import br.com.fiap.Model.Usuario;
import br.com.fiap.Repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username = email
        Usuario u = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new User(
                u.getEmail(),
                u.getSenha(),
                u.isEnabled(),
                true, true, true,
                u.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getNome()))
                        .collect(Collectors.toSet())
        );
    }
}


