package br.com.streaming.controller;
import br.com.streaming.service.MidiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final MidiaService service;

    public DashboardController(MidiaService service) {
        this.service = service;
    }

    @GetMapping
    public String dashboard(@RequestParam(defaultValue = "TOP_AVALIADOS") String criterio, Model model) {
        model.addAttribute("total",          service.contarTotal());
        model.addAttribute("filmes",         service.contarFilmes());
        model.addAttribute("series",         service.contarSeries());
        model.addAttribute("podcasts",       service.contarPodcasts());
        model.addAttribute("lives",          service.contarLives());
        model.addAttribute("mediaAvaliacao", String.format("%.1f", service.mediaAvaliacao()));
        model.addAttribute("recomendacoes",  service.recomendar(criterio, 6));
        model.addAttribute("criterio",       criterio);
        return "dashboard";
    }
}
