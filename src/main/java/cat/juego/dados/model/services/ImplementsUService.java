package cat.juego.dados.model.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
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
import cat.juego.dados.model.repository.PartidaRepository;
import cat.juego.dados.model.repository.UserRepository;

@Service
public class ImplementsUService implements UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository usuarios;

	@Autowired
	private PartidaRepository partidasRepo;

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

	public Usuario buscarUsuario(String username) {

		Usuario usuario = null;
		try {
			usuario = usuarios.findByNombre(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	private Collection<? extends GrantedAuthority> authMappingRol(Collection<Rol> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> getAllUsers() {
		return usuarios.findAll();
	}

	@Override
	public List<Partida> getPartidas(int id_usuario) {

		ArrayList<Partida> partidas = new ArrayList<>();

		for (int i = 0; i < partidasRepo.findAll().size(); i++) {
			if (partidasRepo.findAll().get(i).getUsuario_id() == id_usuario) {
				partidas.add(partidasRepo.findAll().get(i));
			}

		}

		return partidas;
	}

	@Override
	public Usuario findById(int id_usuario) {
		return usuarios.findById(id_usuario).get();
	}

	@Override
	public Usuario saveUser(Usuario usuario) {
		return usuarios.save(usuario);
	}

	@Override
	public Usuario buscarUsuarioDosVeces(String nombre) {
		Usuario usuario = null;
		int i = 0;
		int j = 0;
		boolean encontrado = false;
		while (i < usuarios.findAll().size() && !encontrado) {
			if (usuarios.findAll().get(i).getNombre().equalsIgnoreCase(nombre)) {
				j++;
				if (j == 2) {
					usuario = usuarios.findAll().get(i);
					encontrado = true;
				}

			}
			i++;

		}

		return usuario;
	}

	@Override
	public double calularRanquingAbsoluto() {
		int victorias = 0;
		int derrotas = 0;
		if (!(partidasRepo.findAll() == null)) {
			for (int j = 0; j < partidasRepo.findAll().size(); j++) {
				if (partidasRepo.findAll().get(j).getResultado().equalsIgnoreCase("victory")) {
					victorias++;
				} else {
					derrotas++;
				}
			}
		}
		double media;
		if (!(victorias == 0 && derrotas == 0)) {
			media = victorias / (victorias + derrotas);
		}

		media = 0;
		return media;		
	}

	@Override
	public List<Partida> todasLasPartidas() {
		return partidasRepo.findAll();
	}

}
