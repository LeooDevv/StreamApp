package br.com.streaming.strategy;
import br.com.streaming.model.Midia;
import java.util.List;

public interface RecomendacaoStrategy {
    List<Midia> recomendar(List<Midia> catalogo, int limite);
    String getNome();
    String getDescricao();
}
