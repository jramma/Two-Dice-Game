package cat.juego.dados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.dto.UsuarioRegistroDto;
import cat.juego.dados.model.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/players")
public class DadosController {

	@Autowired
	private UserService service;

//	POST: /players/add: crea un jugador/a. 
// http://localhost:9001/players
	@ModelAttribute("usuario")
	public UsuarioRegistroDto usuarioNuevoDto() {
		return new UsuarioRegistroDto();
	}

	@GetMapping
	public String mostrarFrmulario() {
		return "login";
	}

	@PostMapping
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioRegistroDto registroDto) {
		String respuesta;
		if (service.yaExiste(registroDto.getNombre()) == null) {
			service.saveUser(registroDto);
			respuesta = "redirect:/players?exito";
		} else {
			respuesta = "redirect:/players?error";
		}
		return respuesta;

	}
	//----------------------------------------------------------------------------------------------
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/create")
	public String abrirSesion(@ModelAttribute("usuario") Usuario usuario) {
	
	return "cuenta";

	}
}
