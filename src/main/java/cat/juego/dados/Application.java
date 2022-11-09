package cat.juego.dados;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
//		Usuario anonim = new Usuario("Anonim","1234");
//		Usuario usuario = new Usuario("Juan","1234");
//		service.saveUser(anonim); 
//		service.saveUser(usuario);
	}

}
