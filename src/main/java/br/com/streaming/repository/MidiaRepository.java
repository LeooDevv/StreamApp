package br.com.streaming.repository;
import br.com.streaming.model.Midia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Long> {

    List<Midia> findByTituloContainingIgnoreCase(String titulo);

    List<Midia> findByGeneroIgnoreCase(String genero);

    @Query("SELECT m FROM Midia m WHERE m.avaliacao >= :minimo ORDER BY m.avaliacao DESC")
    List<Midia> findByAvaliacaoMinima(@Param("minimo") double minimo);

    List<Midia> findByTituloContainingIgnoreCaseOrGeneroContainingIgnoreCase(String titulo, String genero);
}
