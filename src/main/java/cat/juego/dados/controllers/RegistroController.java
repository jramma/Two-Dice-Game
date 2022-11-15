package cat.juego.dados.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RegistroController {
	//http://localhost:9001/login
	@GetMapping("/login")
	public String iniciarSesion() {
		return "inicio_sesion";
	}

	
	
	@GetMapping("/")
	public String verInicio() {
		return "cuenta";
	}
	
	
}
