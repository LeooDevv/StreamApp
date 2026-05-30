package br.com.streaming.factory;
import br.com.streaming.dto.MidiaDTO;
import br.com.streaming.model.*;

public class MidiaFactory {

    public static Midia criar(MidiaDTO dto) {
        Midia midia = switch (dto.getTipo().toUpperCase()) {
            case "FILME"   -> criarFilme(dto);
            case "SERIE"   -> criarSerie(dto);
            case "PODCAST" -> criarPodcast(dto);
            case "LIVE"    -> criarLive(dto);
            default -> throw new IllegalArgumentException("Tipo de midia invalido: " + dto.getTipo());
        };
        midia.setId(dto.getId());
        midia.setTitulo(dto.getTitulo());
        midia.setGenero(dto.getGenero());
        midia.setAnoLancamento(dto.getAnoLancamento());
        midia.setDescricao(dto.getDescricao());
        midia.setAvaliacao(dto.getAvaliacao());
        return midia;
    }

    public static MidiaDTO toDTO(Midia m) {
        MidiaDTO dto = new MidiaDTO();
        dto.setId(m.getId());
        dto.setTipo(m.getTipoMidia());
        dto.setTitulo(m.getTitulo());
        dto.setGenero(m.getGenero());
        dto.setAnoLancamento(m.getAnoLancamento());
        dto.setDescricao(m.getDescricao());
        dto.setAvaliacao(m.getAvaliacao());

        if (m instanceof Filme f) {
            dto.setDuracaoMinutos(f.getDuracaoMinutos());
            dto.setDiretor(f.getDiretor());
            dto.setClassificacaoIndicativa(f.getClassificacaoIndicativa());
        } else if (m instanceof Serie s) {
            dto.setNumTemporadas(s.getNumTemporadas());
            dto.setNumEpisodios(s.getNumEpisodios());
            dto.setStatus(s.getStatus());
        } else if (m instanceof Podcast p) {
            dto.setApresentador(p.getApresentador());
            dto.setNumEpsPodcast(p.getNumEpisodios());
            dto.setPeriodicidade(p.getPeriodicidade());
        } else if (m instanceof Live l) {
            dto.setApresentadorLive(l.getApresentador());
            dto.setPlataforma(l.getPlataforma());
            dto.setAoVivo(l.isAoVivo());
        }
        return dto;
    }

    private static Filme criarFilme(MidiaDTO dto) {
        Filme f = new Filme();
        f.setDuracaoMinutos(dto.getDuracaoMinutos() != null ? dto.getDuracaoMinutos() : 90);
        f.setDiretor(dto.getDiretor() != null ? dto.getDiretor() : "Desconhecido");
        f.setClassificacaoIndicativa(dto.getClassificacaoIndicativa() != null ? dto.getClassificacaoIndicativa() : "Livre");
        return f;
    }

    private static Serie criarSerie(MidiaDTO dto) {
        Serie s = new Serie();
        s.setNumTemporadas(dto.getNumTemporadas() != null ? dto.getNumTemporadas() : 1);
        s.setNumEpisodios(dto.getNumEpisodios() != null ? dto.getNumEpisodios() : 10);
        s.setStatus(dto.getStatus() != null ? dto.getStatus() : "EM_ANDAMENTO");
        return s;
    }

    private static Podcast criarPodcast(MidiaDTO dto) {
        Podcast p = new Podcast();
        p.setApresentador(dto.getApresentador() != null ? dto.getApresentador() : "Desconhecido");
        p.setNumEpisodios(dto.getNumEpsPodcast() != null ? dto.getNumEpsPodcast() : 1);
        p.setPeriodicidade(dto.getPeriodicidade() != null ? dto.getPeriodicidade() : "SEMANAL");
        return p;
    }

    private static Live criarLive(MidiaDTO dto) {
        Live l = new Live();
        l.setApresentador(dto.getApresentadorLive() != null ? dto.getApresentadorLive() : "Desconhecido");
        l.setPlataforma(dto.getPlataforma() != null ? dto.getPlataforma() : "YouTube");
        l.setAoVivo(dto.getAoVivo() != null && dto.getAoVivo());
        return l;
    }
}
