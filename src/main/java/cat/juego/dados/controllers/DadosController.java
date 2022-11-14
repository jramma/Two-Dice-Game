package cat.juego.dados.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

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

		service.saveUser(registroDto);
		return "redirect:/login?exito";

	}

}
