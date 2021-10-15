package br.com.thianolima.news.noticia.repositories;

import br.com.thianolima.news.noticia.entities.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long> {

    @Query("from NoticiaEntity n where n.ativo = true and n.id = :id")
    Optional<NoticiaEntity> pesquisarPorId(@Param("id") Long id);
}
