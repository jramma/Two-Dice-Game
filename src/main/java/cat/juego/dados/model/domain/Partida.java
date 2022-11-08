package cat.juego.dados.model.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "partidas")
public class Partida implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_partida;
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Column(name = "dado1", nullable = false)	
	private int dado1;
	
	@Column(name = "dado2", nullable = false)	
	private int dado2;
	
	@Column(name = "resultado", nullable = false)	
	private String resultado;
	
	
	public Partida(Usuario usuario, int dado1, int dado2) {
		String resultado1;
		this.usuario = usuario;
		this.dado1 = dado1;
		this.dado2 = dado2;
		if(dado1+dado2==7) {
			resultado1 = "victory";
		}else {
			resultado1 = "you lose";
		}
		this.resultado = resultado1;
	}
	
	public Partida() {
	}


	@Override
	public String toString() {
		return "Partida [usuario=" + usuario + ", dado1=" + dado1 + ", dado2=" + dado2 + ", resultado=" + resultado
				+ "]";
	}
	
	
	
}
