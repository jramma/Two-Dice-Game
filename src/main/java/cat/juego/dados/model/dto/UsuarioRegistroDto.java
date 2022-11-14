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

	public UsuarioRegistroDto(String nombre, String password, String date) {
		this.nombre = nombre;
		this.password = password;
		this.ranquing = 0;
		this.partidas = new ArrayList<>();
		this.date = date;
	}

	public UsuarioRegistroDto(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
		// no hay valores para el resto
	}

	public UsuarioRegistroDto(String nombre) {
		this.nombre = nombre;
	}

}
