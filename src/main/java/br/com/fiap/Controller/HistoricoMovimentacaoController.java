package br.com.fiap.Controller;

import br.com.fiap.Model.HistoricoMovimentacao;
import br.com.fiap.Service.HistoricoMovimentacaoService;
import br.com.fiap.Service.MotoService;
import br.com.fiap.Service.ZonaVirtualService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historicos")
public class HistoricoMovimentacaoController {

    private final HistoricoMovimentacaoService service;
    private final MotoService motoService;
    private final ZonaVirtualService zonaService;

    public HistoricoMovimentacaoController(HistoricoMovimentacaoService service,
                                           MotoService motoService,
                                           ZonaVirtualService zonaService) {
        this.service = service;
        this.motoService = motoService;
        this.zonaService = zonaService;
    }

    @ModelAttribute
    public void carregarCombos(Model model) {
        model.addAttribute("motos", motoService.listar());
        model.addAttribute("zonas", zonaService.listar());
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("historicos", service.listar());
        return "historicos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("historicoMovimentacao", new HistoricoMovimentacao());
        return "historicos/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("historicoMovimentacao") HistoricoMovimentacao historico,
                         BindingResult result) {
        if (result.hasErrors()) return "historicos/form";
        service.salvar(historico);
        return "redirect:/historicos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("historicoMovimentacao", service.buscarPorId(id));
        return "historicos/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("historicoMovimentacao") HistoricoMovimentacao historico,
                            BindingResult result) {
        if (result.hasErrors()) return "historicos/form";
        historico.setId(id);                 // garante o ID no objeto vindo do form
        service.atualizar(id, historico);    // atualiza
        return "redirect:/historicos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/historicos";
    }
}
