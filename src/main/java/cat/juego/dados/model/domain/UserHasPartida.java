package cat.juego.dados.model.domain;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "UserHasPartida")
public class UserHasPartida {
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name="id_partida")
	private Partida partida;

}
