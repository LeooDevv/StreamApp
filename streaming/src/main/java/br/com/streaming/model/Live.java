package br.com.streaming.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("LIVE")
@Getter @Setter @NoArgsConstructor
public class Live extends Midia {

    @Column(length = 60)
    private String apresentador;

    @Column(length = 30)
    private String plataforma;

    @Column
    private boolean aoVivo;

    @Override public String getTipoMidia() { return "LIVE"; }

    @Override
    public String getInfoEspecifica() {
        return "Apresentador: " + apresentador + " | " + plataforma + (aoVivo ? " | AO VIVO" : "");
    }

    @Override public String getIcone() { return "bi-broadcast"; }
}
