package br.com.thianolima.news.assinante.repositories;

import br.com.thianolima.news.assinante.entities.AssinanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssinanteRepository extends JpaRepository<AssinanteEntity, Long> {
    @Query("from AssinanteEntity a where a.ativo = true and a.id = :id")
    Optional<AssinanteEntity> pesquisarPorId(@Param("id") Long id);

    @Query("from AssinanteEntity a where a.ativo = true")
    List<AssinanteEntity> listarAtivos();
}
