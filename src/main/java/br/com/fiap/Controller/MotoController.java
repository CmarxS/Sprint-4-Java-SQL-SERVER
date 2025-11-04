package br.com.fiap.Controller;

import br.com.fiap.Model.Moto;
import br.com.fiap.Service.MotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    // Listar motos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motos", motoService.listar());
        return "motos/lista"; // thymeleaf -> templates/motos/lista.html
    }

    // Formulário de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("moto", new Moto());
        return "motos/form";
    }

    // Salvar cadastro
    @PostMapping
    public String salvar(@Valid @ModelAttribute("moto") Moto moto, BindingResult result) {
        if (result.hasErrors()) {
            return "motos/form";
        }
        motoService.salvar(moto);
        return "redirect:/motos";
    }

    // Editar (carrega formulário com dados)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("moto", motoService.buscarPorId(id));
        return "motos/form";
    }

    // Atualizar (submit do form de edição)
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("moto") Moto moto,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "motos/form";
        }
        motoService.atualizar(id, moto);
        return "redirect:/motos";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        motoService.excluir(id);
        return "redirect:/motos";
    }
}


