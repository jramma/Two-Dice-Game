package cat.juego.dados.model.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Rol;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.dto.UsuarioRegistroDto;
import cat.juego.dados.model.repository.UserRepository;

@Service
public class ImplementsUService implements UserService {

	@Autowired
	private UserRepository usuarios;



	@Override
	public Usuario saveUser(UsuarioRegistroDto registroDto) {
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
		// el 0 es el ranquing>
		Usuario usuario = new Usuario(registroDto.getNombre(), registroDto.getPassword(), 0, new ArrayList<Partida>(),
				dateFormat.format(Calendar.getInstance().getTime()), Arrays.asList(new Rol("ROLE_USER")));

		return usuarios.save(usuario);
	}

}
