package br.com.streaming.controller;
import br.com.streaming.dto.MidiaDTO;
import br.com.streaming.model.Midia;
import br.com.streaming.service.MidiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/midias")
public class MidiaRestController {

    private final MidiaService service;

    public MidiaRestController(MidiaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Midia>> listar(@RequestParam(required = false) String busca) {
        return ResponseEntity.ok(service.buscar(busca));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Midia> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Midia> criar(@Valid @RequestBody MidiaDTO dto) {
        dto.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Midia> atualizar(@PathVariable Long id, @Valid @RequestBody MidiaDTO dto) {
        service.buscarPorId(id);
        dto.setId(id);
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recomendacoes")
    public ResponseEntity<List<Midia>> recomendacoes(
            @RequestParam(defaultValue = "TOP_AVALIADOS") String criterio,
            @RequestParam(defaultValue = "5") int limite) {
        return ResponseEntity.ok(service.recomendar(criterio, limite));
    }
}
