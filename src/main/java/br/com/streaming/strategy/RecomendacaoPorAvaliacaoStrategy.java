package br.com.streaming.strategy;
import br.com.streaming.model.Midia;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;

@Component
public class RecomendacaoPorAvaliacaoStrategy implements RecomendacaoStrategy {

    @Override
    public List<Midia> recomendar(List<Midia> catalogo, int limite) {
        return catalogo.stream()
                .sorted(Comparator.comparingDouble(Midia::getAvaliacao).reversed())
                .limit(limite)
                .toList();
    }

    @Override public String getNome() { return "TOP_AVALIADOS"; }

    @Override
    public String getDescricao() { return "Melhores avaliados pelos usuarios"; }
}
