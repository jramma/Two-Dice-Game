package cat.juego.dados.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
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
		return "login1"; // te devueve el html
	}

	@PostMapping("/players")
	public String saveUser(@ModelAttribute("usuario") Usuario usuario) {
		String respuesta ;
		Usuario usuario1 = buscarUsuario(usuario);
		if (usuario1 == null) {
			respuesta = "error";
		}else {
			try {
				service.saveUser(usuario);
				respuesta = "redirect:/players/getAll";
			} catch (Exception e) {
				respuesta = "error";
			}
		}

		
		return respuesta ;
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

//	@PutMapping("/players/update")
//	public String updateUser(Model model) {
//		Usuario usuario = new Usuario();
//		model.addAttribute("usuario", usuario);
//		return "update";
//	}

	// POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels
	// daus.
	@PostMapping(value = "/players/{id}/games/")
	public String jugar(@PathVariable("id") Integer id) {
			Partida partida = service.jugar(service.findById(id));
			service.guardarPartida(partida, service.findById(id));
		
		return "juego";
	}

	// DELETE /players/{id}/games: elimina les tirades del jugador/a.
	@DeleteMapping(value = "/players/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		String respuesta;
		try {
			service.deletePartidasUser(id);
			respuesta="redirect:/players";
		} catch (Exception e) {
			respuesta= "error";
		}
		return respuesta;
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
	@GetMapping("/players/{id}/games ")
	public String getJugadorRanq(@PathVariable int id, Model model) {
		model.addAttribute("jugador", service.findById(id));
		model.addAttribute("jugadas", service.listaJugadas(service.findById(id)));
		return "stats"; // te devueve el html
	}

	// GET /players/ranking: retorna el ranking mig de tots els jugadors/es del
	// sistema. És a dir, el percentatge mitjà d’èxits.
	@GetMapping("/players/ranking")
	public String getJugadoresRanq(Model model) {
		model.addAttribute("jugador", service.jugadores());
		ArrayList<String> nombres = new ArrayList<String>();
		ArrayList<String> ranquings = new ArrayList<String>();
		 
		for(int i = 0; i < service.jugadores().size();i++) {
			nombres.add(service.jugadores().get(i).getNombre());
			ranquings.add(String.valueOf(service.jugadores().get(i).getRanquing()));
			i++;
		}
		double ranquingAbsoluto=0;
		
		if(service.listaJugadas().size()!=0) {
		ranquingAbsoluto= service.victoriasTotales()/service.listaJugadas().size();
		}
		model.addAttribute("ranquing", ranquings);
		
		model.addAttribute("ranquingAbsoluto",String.valueOf(ranquingAbsoluto));
		return "stats"; // te devueve el html
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
	
}
