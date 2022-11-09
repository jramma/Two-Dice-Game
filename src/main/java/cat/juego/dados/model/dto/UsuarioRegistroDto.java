package cat.juego.dados.model.dto;

import java.util.ArrayList;
import java.util.List;

import cat.juego.dados.model.domain.Partida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDto {
	private int id_usuario;

	private String nombre;

	private String password;

	private double ranquing;

	private List<Partida> partidas = new ArrayList<>();

	private String date;
	
	
}
