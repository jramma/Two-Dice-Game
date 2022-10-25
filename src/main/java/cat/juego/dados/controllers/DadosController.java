package cat.juego.dados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/players")
public class DadosController {

	@Autowired
	private UserService service;

//	POST: /players/add: crea un jugador/a. 

	@PostMapping({ "/add" })
	public String addUsuario(@RequestBody Usuario usuario, Model model) {
		ResponseEntity<?> respuesta = null;
		Usuario usuario1 = buscarUsuario(usuario);
		if (usuario1 == null) {
			respuesta = new ResponseEntity<String>("El usuario ya existe", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				Usuario usuarioGuardado = service.addUser(usuario);
				respuesta = new ResponseEntity<Usuario>(usuarioGuardado, HttpStatus.CREATED);
			} catch (Exception e) {
				respuesta = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// si el usuario ya existe el nombre se queda como anónimo
		return "login";
	}

	private Usuario buscarUsuario(Usuario usuario) {
		List<Usuario> usuarios = service.jugadores();
		int i = 0;
		boolean encontrado = false;
		while (i < usuarios.size() && !encontrado) {
			if (usuarios.get(i).getNombre().equals(usuario.getNombre())) {
				if (!(usuario.getNombre().equals("ANONIM"))) {
					usuario = null;
				}
				encontrado = true;
			}
			i++;
		}

		return usuario;
	}

// PUT /players: modifica el nom del jugador/a.

	@PutMapping("/update")
	public String updateUser(@RequestBody Usuario usuario) {
		ResponseEntity<?> respuesta = null;
		try {
			Usuario userS = service.addUser(usuario);
			respuesta = new ResponseEntity<Usuario>(userS, HttpStatus.CREATED);
		} catch (Exception e) {
			respuesta = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return "update";
	}

	// POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels
	// daus.
	@PostMapping(value = "/players/{id}/games/")
	public String jugar(@PathVariable("id") Integer id) {
		ResponseEntity<?> respuesta = null;
		try {
			Partida partida = service.jugar(service.findById(id));
			respuesta = new ResponseEntity<String>(partida.toString() + " deleted", HttpStatus.OK);
			service.guardarPartida(partida, service.findById(id));
		} catch (Exception e) {
			respuesta = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "juego";
	}

	// DELETE /players/{id}/games: elimina les tirades del jugador/a.
	@DeleteMapping(value = "/players/{id}/games/")
	public String delete(@PathVariable("id") Integer id) {
		ResponseEntity<?> respuesta = null;
		try {
			service.deletePartidasUser(id);
			respuesta = new ResponseEntity<String>(service.findById(id).toString() + " deleted", HttpStatus.OK);
		} catch (Exception e) {
			respuesta = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "redirect:/add";
	}

	// GET /players/: retorna el llistat de tots els jugadors/es del sistema amb el
	// seu percentatge mitjà d’èxits.
	@GetMapping
	public String getJugadorsRanq(Model model) {
		model.addAttribute("jugadores", service.jugadores());
		model.addAttribute("partidas", service.listaJugadas());
		return "stats"; // te devueve el html
	}

	// GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
	@GetMapping(" /players/{id}/games ")
	public String getJugadorRanq(@PathVariable int id, Model model) {
		model.addAttribute("jugador", service.findById(id));
		model.addAttribute("jugadas", service.listaJugadas(service.findById(id)));
		return "stats"; // te devueve el html
	}

	// GET /players/ranking: retorna el ranking mig de tots els jugadors/es del
	// sistema. És a dir, el percentatge mitjà d’èxits.
	@GetMapping("/players/ranking")
	public String getJugadorRanq(Model model) {
		model.addAttribute("jugador", service.jugadores());

		List<String> ranquings = null;
		service.jugadores().forEach(n -> ranquings.add(String.valueOf(n.getRanquing())));
		model.addAttribute("ranquing", ranquings);
		return "stats"; // te devueve el html
	}

	// GET /players/ranking/loser: retorna el jugador/a amb pitjor percentatge
	// d’èxit.
	@GetMapping("/ranking/loser")
	public String getWorst(Model model) {
		model.addAttribute("jugador", service.peorUsuario());
		model.addAttribute("ranquing", service.peorUsuario().getRanquing());
		return "stats"; // te devueve el html
	}

	// GET /players/ranking/winner: retorna el jugador amb pitjor percentatge
	// d’èxit.
	@GetMapping("/ranking/winner")
	public String getBest(Model model) {
		model.addAttribute("jugador", service.mejorUsuario());
		model.addAttribute("ranquing", service.mejorUsuario().getRanquing());
		return "stats"; // te devueve el html
	}
}
