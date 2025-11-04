package br.com.fiap.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider(UserDetailsService uds, PasswordEncoder encoder) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(encoder);
        return p;
    }

    // Liberar H2 console em DEV
    @Bean
    @Order(1)
    SecurityFilterChain h2(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/h2/**")
                .authorizeHttpRequests(a -> a.anyRequest().permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2/**")))
                .headers(h -> h.frameOptions(f -> f.disable()));
        return http.build();
    }

    // Cadeia principal: OPERADOR = leitura; ADMIN = CRUD
    @Bean
    @Order(2)
    SecurityFilterChain app(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // público
                        .requestMatchers("/login", "/css/**", "/js/**", "/img/**").permitAll()

                        // leitura (GET) para ADMIN e OPERADOR
                        .requestMatchers(HttpMethod.GET,
                                "/", "/motos", "/motos/**",
                                "/zonas", "/zonas/**",
                                "/historicos", "/historicos/**"
                        ).hasAnyRole("ADMIN", "OPERADOR")

                        // qualquer rota de criação/edição/exclusão
                        .requestMatchers(
                                "/motos/novo", "/motos/atualizar/**", "/motos/excluir/**",
                                "/zonas/novo", "/zonas/atualizar/**", "/zonas/excluir/**",
                                "/historicos/novo", "/historicos/atualizar/**", "/historicos/excluir/**"
                        ).hasRole("ADMIN")

                        // e por método HTTP (se seus forms usam POST/PUT/DELETE)
                        .requestMatchers(HttpMethod.POST,   "/motos/**", "/zonas/**", "/historicos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/motos/**", "/zonas/**", "/historicos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,  "/motos/**", "/zonas/**", "/historicos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/motos/**", "/zonas/**", "/historicos/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true)
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll()
                )
                .exceptionHandling(e -> e.accessDeniedPage("/acesso-negado"))
                .csrf(Customizer.withDefaults());

        return http.build();
    }
}




