package br.com.streaming.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "midias")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_midia", discriminatorType = DiscriminatorType.STRING, length = 20)
@Getter @Setter @NoArgsConstructor
public abstract class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private int anoLancamento;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double avaliacao; // 0.0 a 5.0

    public abstract String getTipoMidia();

    public abstract String getInfoEspecifica();

    public abstract String getIcone();
}
