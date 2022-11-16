package cat.juego.dados.model.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Partida;
import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.dto.UsuarioRegistroDto;

@Service
public interface UserService extends UserDetailsService {
	public Usuario saveUser(UsuarioRegistroDto registroDto);
	public Usuario saveUser(Usuario usuario);
	public Usuario buscarUsuario(String username);
	public List<Usuario> getAllUsers();
	public List<Partida> getPartidas(int id_usuario);
	public Usuario findById(int id_usuario);
	public Usuario buscarUsuarioDosVeces(String nombre);
	public double calularRanquingAbsoluto();
	public List<Partida> todasLasPartidas();
	public Partida savePartida(Partida partida);
}
