package cat.juego.dados.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;

@Service
public interface UserService {

//	POST: /players: crea un jugador/a.
	public Usuario saveUser(Usuario usuario);

//	PUT /players: modifica el nom del jugador/a.

//	POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.  
	public Partida jugar(int id);
	public void guardarPartida(Partida partida, Usuario usuario);
//	DELETE /players/{id}/games: elimina les tirades del jugador/a.
	public void deletePartidasUser(Integer id);

//	GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits. 
	public List<Usuario> jugadores();

//	GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.  
	public List<Partida> listaJugadas(Usuario usuario);

//	GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits. 
	public double ranquingTotal();
//	GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.  
	public Usuario peorUsuario();

//	GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit. 
	public Usuario mejorUsuario();

	
	
	
	public List<Partida> listaJugadas();
	
	public Usuario findById(int id);
	
	public int victoriasTotales();


}
