package br.com.streaming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("SERIE")
@Getter @Setter @NoArgsConstructor
public class Serie extends Midia {

    @Column
    private int numTemporadas;

    @Column
    private int numEpisodios;

    @Column(length = 20)
    private String status;

    @Override public String getTipoMidia() { return "SERIE"; }

    @Override
    public String getInfoEspecifica() {
        return numTemporadas + " temp. | " + numEpisodios + " eps. | " + status;
    }

    @Override public String getIcone() { return "bi-display"; }
}
