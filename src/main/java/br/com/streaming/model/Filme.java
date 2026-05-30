package br.com.streaming.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("FILME")
@Getter @Setter @NoArgsConstructor
public class Filme extends Midia {

    @Column
    private int duracaoMinutos;

    @Column(length = 50)
    private String diretor;

    @Column(length = 20)
    private String classificacaoIndicativa;

    @Override public String getTipoMidia() { return "FILME"; }

    @Override
    public String getInfoEspecifica() {
        return duracaoMinutos + " min | Dir: " + diretor + " | " + classificacaoIndicativa;
    }

    @Override public String getIcone() { return "bi-film"; }
}
