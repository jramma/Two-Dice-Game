package cat.juego.dados.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.UserHasPartida;
import cat.juego.dados.model.domain.Usuario;

@Service
public interface UserService {

	public Usuario saveUser(Usuario usuario);

	public UserHasPartida jugar(Usuario usuario);


	public void deletePartidasUser(Integer id);

	public List<Usuario> jugadores();

	public List<Partida> listaJugadas(Usuario usuario);

	public double ranquingTotal();

	public Usuario peorUsuario();

	public Usuario mejorUsuario();

	public List<Partida> listaJugadas();

	public Usuario findById(int id);

	public int victoriasTotales();

}
