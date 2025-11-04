package br.com.fiap.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() { return "auth/login"; }

    @GetMapping("/acesso-negado")
    public String acessoNegado() { return "auth/denied"; }
}

