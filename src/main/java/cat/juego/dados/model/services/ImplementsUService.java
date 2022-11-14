package cat.juego.dados.model.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Rol;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.dto.UsuarioRegistroDto;
import cat.juego.dados.model.repository.UserRepository;

@Service
public class ImplementsUService implements UserService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository usuarios;

	@Override
	public Usuario saveUser(UsuarioRegistroDto registroDto) {
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
		// el 0 es el ranquing>
		Usuario usuario = new Usuario(registroDto.getNombre(),
				
				passwordEncoder.encode(registroDto.getPassword()), 0, new ArrayList<Partida>(),
				dateFormat.format(Calendar.getInstance().getTime()), Arrays.asList(new Rol("ROLE_USER")));

		return usuarios.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarios.findByNombre(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("No existe el usuario");
		}
		return new User(usuario.getNombre(), usuario.getPassword(), authMappingRol(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> authMappingRol(Collection<Rol> roles) {
		return roles.stream().map(rol -> 
		new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

}
