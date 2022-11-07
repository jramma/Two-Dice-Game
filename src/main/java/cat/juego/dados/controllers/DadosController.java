package cat.juego.dados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DadosController {

	@Autowired
	private UserService service;

//	POST: /players/add: crea un jugador/a. 
// http://localhost:9000/players/add

	@GetMapping({ "/players/add" })
	public String addUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "login1";
	}

	@PostMapping("/players")
	public String saveUser(@ModelAttribute("usuario") Usuario usuario) {
		String respuesta;
		Usuario usuario1 = buscarUsuario(usuario.getNombre());
		if (usuario1 != null) {
			respuesta = "redirect:/players/add";
		} else {
			try {
				service.saveUser(usuario);
				respuesta = "redirect:/players/getAll";
			} catch (Exception e) {
				respuesta = "redirect:/players/add";
			}
		}

		return respuesta;
	}

	private Usuario buscarUsuario(String nombre) {
		List<Usuario> usuarios = service.jugadores();
		Usuario usuario = null;
		int i = 0;
		boolean encontrado = false;
		while (i < usuarios.size() && !encontrado) {
			if (usuarios.get(i).getNombre().equals(nombre)) {
				if (!(nombre.equals("ANONIM"))) {
					usuario = usuarios.get(i);
				}
				encontrado = true;
			}
			i++;
		}

		return usuario;
	}

	// PUT /players: modifica el nom del jugador/a.

	// http://localhost:9000/players/update/1
	@GetMapping("/players/update/{id}")
	public String updateUser(@PathVariable Integer id, Model model) {
		model.addAttribute("usuario", service.findById(id));
		return "update";
	}

	@PostMapping("/updating/{id}")
	public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
		String respuesta;
		int repeticiones = buscarQueNoSeRepitaDosVeces(usuario.getNombre());
		if (repeticiones < 2) {
			Usuario usuarioExiste = service.findById(usuario.getId_usuari());
			usuarioExiste.setNombre(usuario.getNombre());
			service.saveUser(usuarioExiste);
			respuesta = "redirect:/players/getAll";
		} else {
			respuesta = "redirect:/players/update/{id}";
		}
		return respuesta;
	}

	private int buscarQueNoSeRepitaDosVeces(String nombre) {
		List<Usuario> usuarios = service.jugadores();
		int i = 0, j = 0;
		boolean encontrado = false;
		while (i < usuarios.size() && !encontrado && j < 2) {
			if (usuarios.get(i).getNombre().equals(nombre)) {
				if (!(nombre.equals("ANONIM"))) {
					j++;
				}

			}
			i++;
		}
		return j;
	}

	// POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels
	// daus.
	// http://localhost:9000/players/1/games/
	@GetMapping("/players/{id}/games/")
	public String jugar(@PathVariable Integer id, Model model) {
		Partida partida = service.jugar(service.findById(id));

		model.addAttribute("partida", partida);

		return "juego";
	}

	@PostMapping("/guardar")
	public String guardarJuego(@PathVariable int id, @ModelAttribute("usuario") Partida partida, Model model) {
		Usuario usuario = buscarJugador(partida.getUsuario());
		usuario.getPartidas().add(partida);
		service.guardarPartida(partida, service.findById(id));
		int i = usuario.getId_usuari();

		return "redirect:/players/cuenta/" + i;
	}

	// http://localhost:9000/players/delete/3
	@GetMapping("/players/delete/{id}")
	public String delete(@PathVariable Integer id) {

		service.deletePartidasUser(id);
		return "redirect:/players/getAll";

	}

	// GET /players/: retorna el llistat de tots els jugadors/es del sistema amb el
	// seu percentatge mitjà d’èxits.
	@GetMapping("/players/getAll")
	public String getJugadorsRanq(Model model) {
		model.addAttribute("jugadores", service.jugadores());

		model.addAttribute("partidas", service.listaJugadas());
		return "stats"; // te devueve el html
	}

	// GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.

	@GetMapping("/players/cuenta/{id}")
	public String getJugadorRanq(@PathVariable int id, Model model) {
		Usuario usuario = service.findById(id);
		model.addAttribute("jugador", usuario);
		model.addAttribute("jugadas", service.listaJugadas(usuario));
		return "cuenta"; // te devueve el html
	}

	// GET /players/ranking: retorna el ranking mig de tots els jugadors/es del
	// sistema. És a dir, el percentatge mitjà d’èxits.
	// http://localhost:9000/players/ranking
	@GetMapping("/players/ranking")
	public String getJugadoresRanq(Model model) {

		double ranquingAbsoluto = 0;

		if (service.listaJugadas().size() != 0) {
			ranquingAbsoluto = service.victoriasTotales() / service.listaJugadas().size();
		}

		model.addAttribute("partidas", service.listaJugadas());
		model.addAttribute("ranquingAbsoluto", String.valueOf(ranquingAbsoluto));
		return "ranquings"; // te devueve el html
	}

	// GET /players/ranking/loser: retorna el jugador/a amb pitjor percentatge
	// d’èxit.
	@GetMapping("/players/ranking/loser")
	public String getWorst(Model model) {
		model.addAttribute("jugador", service.peorUsuario());
		model.addAttribute("ranquing", service.peorUsuario().getRanquing());
		return "stats"; // te devueve el html
	}

	// GET /players/ranking/winner: retorna el jugador amb pitjor percentatge
	// d’èxit.
	@GetMapping("/players/ranking/winner")
	public String getBest(Model model) {
		model.addAttribute("jugador", service.mejorUsuario());
		model.addAttribute("ranquing", service.mejorUsuario().getRanquing());
		return "stats"; // te devueve el html
	}

	private Usuario buscarJugador(String nombre) {
		List<Usuario> usuarios = service.jugadores();
		Usuario usuario = null;
		int i = 0;
		boolean encontrado = false;
		while (i < usuarios.size() && !encontrado) {
			if (usuarios.get(i).getNombre().equals(nombre)) {
				usuario = usuarios.get(i);

				encontrado = true;
			}
			i++;
		}

		return usuario;
	}
}
