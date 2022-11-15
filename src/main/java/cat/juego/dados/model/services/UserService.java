package cat.juego.dados.model.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import cat.juego.dados.model.domain.Usuario;
import cat.juego.dados.model.dto.UsuarioRegistroDto;

@Service
public interface UserService extends UserDetailsService {
	public Usuario saveUser(UsuarioRegistroDto registroDto);
	public Usuario yaExiste(String username);

}
