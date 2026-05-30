package br.com.streaming.controller;
import br.com.streaming.dto.MidiaDTO;
import br.com.streaming.factory.MidiaFactory;
import br.com.streaming.service.MidiaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/midias")
public class MidiaController {

    private final MidiaService service;
    private static final String[] TIPOS = {"FILME", "SERIE", "PODCAST", "LIVE"};

    public MidiaController(MidiaService service) {
        this.service = service;
    }

    @GetMapping
    public String lista(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("midias", service.buscar(busca));
        model.addAttribute("busca", busca);
        return "midias/lista";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("midia", new MidiaDTO());
        model.addAttribute("tipos", TIPOS);
        model.addAttribute("titulo", "Nova Midia");
        return "midias/form";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("midia", MidiaFactory.toDTO(service.buscarPorId(id)));
        model.addAttribute("tipos", TIPOS);
        model.addAttribute("titulo", "Editar Midia");
        return "midias/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("midia") MidiaDTO dto,
                         BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("tipos", TIPOS);
            model.addAttribute("titulo", dto.getId() == null ? "Nova Midia" : "Editar Midia");
            return "midias/form";
        }
        service.salvar(dto);
        redirect.addFlashAttribute("mensagemSucesso",
                dto.getId() == null ? "Midia cadastrada com sucesso!" : "Midia atualizada com sucesso!");
        return "redirect:/midias";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("midia", service.buscarPorId(id));
        return "midias/detalhe";
    }

    /** Deletar */
    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes redirect) {
        service.deletar(id);
        redirect.addFlashAttribute("mensagemSucesso", "Midia removida com sucesso.");
        return "redirect:/midias";
    }
}
