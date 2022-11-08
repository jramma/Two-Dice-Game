package cat.juego.dados.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.UserHasPartida;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.repository.PartidaRepository;
import cat.juego.dados.model.repository.UserRepository;

@Service
public class ImplementsUService implements UserService {

	@Autowired
	private PartidaRepository partidas;

	@Autowired
	private UserRepository usuarios;

	@Override
	public Usuario saveUser(Usuario usuario) {
		return usuarios.save(usuario);

	}

	@Override
	public UserHasPartida jugar(Usuario usuario) {

		Partida partida = new Partida(usuario, (int) (Math.random() * 6), (int) (Math.random() * 6));
		partidas.save(partida);
		usuario.getPartidas().add(partida);
		UserHasPartida userHasPartida = new UserHasPartida(usuario, partida);

		return userHasPartida;
	}

	@Override
	public void deletePartidasUser(Integer id) {
		List<Partida> partidas = new ArrayList<>();
		partidas.removeAll(partidas);
		usuarios.findById(id).get().setPartidas(partidas);

	}

	@Override
	public List<Usuario> jugadores() {
		return usuarios.findAll();
	}

	@Override
	public List<Partida> listaJugadas(Usuario usuario) {
		
		return usuario.getPartidas();
	}

	@Override
	public double ranquingTotal() {
		int victorias = 0, derrotas = 0;
		double media = 0;
		for (int i = 0; i < partidas.findAll().size(); i++) {
				if (partidas.findAll().get(i).getResultado().equalsIgnoreCase("victory")) {
					victorias++;
				} else {
					derrotas++;
				}
			
		}

		media = victorias / (victorias + derrotas);
		return media;

	}

	@Override
	public Usuario peorUsuario() {
		List<Usuario> usuarios1 = usuarios.findAll();
		Usuario looser = null;
		for (int i = 0; i < usuarios1.size() - 1; i++) {
			if (usuarios1.get(i).getRanquing() < usuarios1.get(i + 1).getRanquing()) {
				looser = usuarios1.get(i);
			}
		}

		return looser;
	}

	@Override
	public Usuario mejorUsuario() {
		List<Usuario> usuarios1 = usuarios.findAll();
		Usuario winner = null;
		for (int i = 0; i < usuarios1.size() - 1; i++) {
			if (usuarios1.get(i).getRanquing() < usuarios1.get(i + 1).getRanquing()) {
				winner = usuarios1.get(i);
			}
		}

		return winner;
	}

	@Override
	public List<Partida> listaJugadas() {
		return partidas.findAll();

	}

	@Override
	public Usuario findById(int id) {
		return (Usuario) usuarios.findById(id).get();
	}

	@Override
	public int victoriasTotales() {
		int victorias = 0;

		for (int i = 0; i < partidas.findAll().size(); i++) {
			if (partidas.findById(i).get().getResultado().equals("victory")) {
				victorias++;
			}
		}

		return victorias;
	}
}
