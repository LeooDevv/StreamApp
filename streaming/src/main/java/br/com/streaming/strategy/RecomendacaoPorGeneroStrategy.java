package br.com.streaming.strategy;
import br.com.streaming.model.Midia;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RecomendacaoPorGeneroStrategy implements RecomendacaoStrategy {

    @Override
    public List<Midia> recomendar(List<Midia> catalogo, int limite) {
        // Encontra o genero com mais titulos
        String generoPopular = catalogo.stream()
                .collect(Collectors.groupingBy(Midia::getGenero, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        return catalogo.stream()
                .filter(m -> m.getGenero().equalsIgnoreCase(generoPopular))
                .sorted(Comparator.comparingDouble(Midia::getAvaliacao).reversed())
                .limit(limite)
                .toList();
    }

    @Override public String getNome() { return "GENERO_POPULAR"; }

    @Override
    public String getDescricao() { return "Baseado no genero mais popular do catalogo"; }
}
