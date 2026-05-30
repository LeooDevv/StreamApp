package br.com.streaming.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PODCAST")
@Getter @Setter @NoArgsConstructor
public class Podcast extends Midia {

    @Column(length = 60)
    private String apresentador;

    @Column
    private int numEpisodios;

    @Column(length = 30)
    private String periodicidade;

    @Override public String getTipoMidia() { return "PODCAST"; }

    @Override
    public String getInfoEspecifica() {
        return "Apresentador: " + apresentador + " | " + numEpisodios + " eps. | " + periodicidade;
    }

    @Override public String getIcone() { return "bi-mic"; }
}
