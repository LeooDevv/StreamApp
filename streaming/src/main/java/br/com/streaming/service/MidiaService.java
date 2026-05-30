package br.com.streaming.service;
import br.com.streaming.dto.MidiaDTO;
import br.com.streaming.exception.MidiaNaoEncontradaException;
import br.com.streaming.factory.MidiaFactory;
import br.com.streaming.model.*;
import br.com.streaming.repository.MidiaRepository;
import br.com.streaming.strategy.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MidiaService {

    private final MidiaRepository repository;

    public MidiaService(MidiaRepository repository) {
        this.repository = repository;
    }

    public List<Midia> listarTodas() {
        return repository.findAll();
    }

    public Midia buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MidiaNaoEncontradaException("Midia nao encontrada com id: " + id));
    }

    public List<Midia> buscar(String termo) {
        if (termo == null || termo.isBlank()) return listarTodas();
        return repository.findByTituloContainingIgnoreCaseOrGeneroContainingIgnoreCase(termo, termo);
    }

    public Midia salvar(MidiaDTO dto) {
        Midia midia = MidiaFactory.criar(dto);
        return repository.save(midia);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public List<Midia> recomendar(String criterio, int limite) {
        List<Midia> catalogo = listarTodas();
        RecomendacaoStrategy strategy = switch (criterio.toUpperCase()) {
            case "MAIS_RECENTES"  -> new RecomendacaoMaisRecenteStrategy();
            case "GENERO_POPULAR" -> new RecomendacaoPorGeneroStrategy();
            default               -> new RecomendacaoPorAvaliacaoStrategy();
        };
        return strategy.recomendar(catalogo, limite);
    }

    public long contarTotal()    { return repository.count(); }
    public long contarFilmes()   { return listarTodas().stream().filter(m -> m instanceof Filme).count(); }
    public long contarSeries()   { return listarTodas().stream().filter(m -> m instanceof Serie).count(); }
    public long contarPodcasts() { return listarTodas().stream().filter(m -> m instanceof Podcast).count(); }
    public long contarLives()    { return listarTodas().stream().filter(m -> m instanceof Live).count(); }
    public double mediaAvaliacao() {
        return listarTodas().stream().mapToDouble(Midia::getAvaliacao).average().orElse(0.0);
    }
}
