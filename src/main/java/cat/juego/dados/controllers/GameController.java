package cat.juego.dados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.services.UserService;

@Controller
public class GameController {
	@Autowired
	private UserService userService;

	// http://localhost:9001/login
	@GetMapping("/login")
	public String iniciarSesion() {
		return "inicio_sesion";
	}

	@GetMapping("/")
	public String verInicio(Authentication auth, Model model) {
		Usuario usuario = userService.buscarUsuario(auth.getName());

		List<Partida> partidas = userService.getPartidas(usuario.getId());
		usuario.setRanquing(caluleteRanquing(partidas));
		model.addAttribute("jugador", usuario);
		model.addAttribute("jugadas", partidas);

		return "cuenta";
	}

	public double caluleteRanquing(List<Partida> partidas) {
		int victorias = 0;
		int derrotas = 0;
		double media = 0;

		if (partidas != null) {
			for (int j = 0; j < partidas.size(); j++) {
				if (partidas.get(j).getResultado().equalsIgnoreCase("victory")) {
					victorias++;
				} else {
					derrotas++;
				}
			}
			media =(double)victorias /(victorias + derrotas);
		}
		return media;
	}

	// http://localhost:9001/update

	@GetMapping("/update")
	public String verUpdate(Authentication auth, Model model) {
		Usuario usuario = userService.buscarUsuario(auth.getName());
		model.addAttribute("usuario", usuario);
		return "update";
	}

	@PostMapping("/updating")
	public String updateUser(@ModelAttribute("nombre") String nombre, Authentication auth) {
		Usuario usuario1 = userService.buscarUsuario(auth.getName());
		usuario1.setNombre(nombre);
		userService.saveUser(usuario1);
		String respuesta;
		if (userService.buscarUsuarioDosVeces(nombre) == null) {
			userService.saveUser(usuario1);
			respuesta = "redirect:/login";
		} else {
			respuesta = "redirect:/update?error";
		}
		return respuesta;

	}

	@GetMapping("/ranquings")
	public String ranquings(Model model) {
		model.addAttribute("ranquingAbsoluto", userService.calularRanquingAbsoluto());
		model.addAttribute("partidas", userService.todasLasPartidas());
		return "ranquings";

	}

	@GetMapping("/getAll")
	public String getAll(Model model) {
		for (int i = 0; i < userService.getAllUsers().size(); i++) {

			userService.getAllUsers().get(i)
					.setRanquing(caluleteRanquing(userService.getPartidas(userService.getAllUsers().get(i).getId())));
		}

		model.addAttribute("jugadores", userService.getAllUsers());
		return "stats";

	}

	@GetMapping("/juego")
	public String juego(Model model, Authentication auth) {
		Usuario usuario = userService.buscarUsuario(auth.getName());	
		Partida partida = new Partida(usuario.getId());
		userService.savePartida(partida);	
		model.addAttribute("partida",partida);
		return "juego";

	}
}
