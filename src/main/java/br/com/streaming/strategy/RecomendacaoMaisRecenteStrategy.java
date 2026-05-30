package br.com.streaming.strategy;
import br.com.streaming.model.Midia;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;

@Component
public class RecomendacaoMaisRecenteStrategy implements RecomendacaoStrategy {

    @Override
    public List<Midia> recomendar(List<Midia> catalogo, int limite) {
        return catalogo.stream()
                .sorted(Comparator.comparingInt(Midia::getAnoLancamento).reversed())
                .limit(limite)
                .toList();
    }

    @Override public String getNome() { return "MAIS_RECENTES"; }

    @Override
    public String getDescricao() { return "Lancamentos mais recentes"; }
}
