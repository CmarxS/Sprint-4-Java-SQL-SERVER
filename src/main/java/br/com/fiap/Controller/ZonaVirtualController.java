package br.com.fiap.Controller;

import br.com.fiap.Model.ZonaVirtual;
import br.com.fiap.Service.ZonaVirtualService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zonas")
public class ZonaVirtualController {

    private final ZonaVirtualService service;

    public ZonaVirtualController(ZonaVirtualService service) {
        this.service = service;
    }

    // Listagem
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("zonas", service.listar());
        return "zonas/lista"; // templates/zonas/lista.html
    }

    // Form de criação
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("zonaVirtual", new ZonaVirtual());
        return "zonas/form"; // templates/zonas/form.html
    }

    // Salvar criação
    @PostMapping
    public String salvar(@Valid @ModelAttribute("zonaVirtual") ZonaVirtual zona,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "zonas/form";
        }
        try {
            service.salvar(zona);
        } catch (IllegalArgumentException e) {
            // Mostra o erro no campo "nome"
            result.rejectValue("nome", "duplicado", e.getMessage());
            return "zonas/form";
        }
        return "redirect:/zonas";
    }

    // Carrega form de edição
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("zonaVirtual", service.buscarPorId(id));
        return "zonas/form";
    }

    // Atualiza
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("zonaVirtual") ZonaVirtual zona,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "zonas/form";
        }
        try {
            service.atualizar(id, zona);
        } catch (IllegalArgumentException e) {
            result.rejectValue("nome", "duplicado", e.getMessage());
            return "zonas/form";
        }
        return "redirect:/zonas";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/zonas";
    }
}

