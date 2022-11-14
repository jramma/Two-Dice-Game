package cat.juego.dados.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.juego.dados.model.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository <Usuario, Integer> {

	public Usuario findByNombre(String nombre);
}
