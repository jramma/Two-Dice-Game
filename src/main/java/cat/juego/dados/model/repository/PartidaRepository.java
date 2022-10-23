package cat.juego.dados.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.juego.dados.model.domain.Partida;

@Repository
public interface PartidaRepository extends JpaRepository <Partida, Integer> {

}
