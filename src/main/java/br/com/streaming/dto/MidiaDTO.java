package br.com.streaming.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MidiaDTO {

    private Long id;

    @NotBlank(message = "Tipo e obrigatorio")
    private String tipo;

    @NotBlank(message = "Titulo e obrigatorio")
    private String titulo;

    @NotBlank(message = "Genero e obrigatorio")
    private String genero;

    @Min(value = 1888, message = "Ano invalido")
    private int anoLancamento;

    @NotBlank(message = "Descricao e obrigatoria")
    private String descricao;

    @Min(0) @Max(5)
    private double avaliacao;

    // Filme
    private Integer duracaoMinutos;
    private String diretor;
    private String classificacaoIndicativa;

    // Serie
    private Integer numTemporadas;
    private Integer numEpisodios;
    private String status;

    // Podcast
    private String apresentador;
    private Integer numEpsPodcast;
    private String periodicidade;

    // Live
    private String apresentadorLive;
    private String plataforma;
    private Boolean aoVivo;
}
