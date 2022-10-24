package cat.juego.dados.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;

@Service
public interface UserService {

//	POST: /players: crea un jugador/a.
	public Usuario addUser(Usuario usuario);

//	PUT /players: modifica el nom del jugador/a.
	public Usuario updateUser(Usuario usuario);

//	POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.  
	public Partida jugar(Usuario usuasrio);
	public void guardarPartida(Partida partida, Usuario usuario);
//	DELETE /players/{id}/games: elimina les tirades del jugador/a.
	public Usuario deletePartidasUser(Usuario usuario);

//	GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits. 
	public List<Usuario> jugadoresYSusRanquings();

//	GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.  
	public ArrayList<Partida> listaJugadas(Usuario usuario);

//	GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits. 
	public double ranquingTotal();
//	GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.  
	public Usuario peorUsuario();

//	GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit. 
	public Usuario mejorUsuario();

	public List<Partida> listaJugadas();

}
