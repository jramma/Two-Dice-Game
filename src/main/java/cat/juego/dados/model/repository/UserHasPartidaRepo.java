package cat.juego.dados.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;

@Repository
public interface UserHasPartidaRepo extends JpaRepository<Usuario, Partida>{

}
