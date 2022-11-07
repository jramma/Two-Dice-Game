package cat.juego.dados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.services.UserService;

@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private UserService service;
	@Override
	public void run(String... args) throws Exception {
		//Usuario usuario = new Usuario("Juan","1234");
		// service.saveUser(usuario);
	}

}
