package cat.juego.dados.model.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "partidas")
@AllArgsConstructor
@NoArgsConstructor
public class Partida implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "usuario_id", nullable = false)
	private int usuario_id;
	
	@Column(name = "dado1", nullable = false)	
	private int dado1;
	
	@Column(name = "dado2", nullable = false)	
	private int dado2;
	
	@Column(name = "resultado", nullable = false)	
	private String resultado;
	
	
	public Partida(int usuario_id) {
		String resultado1;
		this.dado1 =(int)(Math.random()*6+1);
		this.dado2 = (int)(Math.random()*6+1);
		if(dado1+dado2==7) {
			resultado1 = "victory";
		}else {
			resultado1 = "you lose";
		}
		this.resultado = resultado1;
		this.usuario_id = usuario_id;
	}
	
	


	
	
	
}
