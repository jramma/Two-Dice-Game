package cat.juego.dados.model.domain;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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
	private int id;
	
	@ManyToOne(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="Partida_has_User",
	joinColumns = @JoinColumn(name="partida", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="usuario", referencedColumnName = "id")		
			)
	private Usuario usuario_id;
	
	@Column(name = "dado1", nullable = false)	
	private int dado1;
	
	@Column(name = "dado2", nullable = false)	
	private int dado2;
	
	@Column(name = "resultado", nullable = false)	
	private String resultado;
	
	
	public Partida(int dado1, int dado2) {
		String resultado1;
		this.dado1 = dado1;
		this.dado2 = dado2;
		if(dado1+dado2==7) {
			resultado1 = "victory";
		}else {
			resultado1 = "you lose";
		}
		this.resultado = resultado1;
	}
	
	


	
	
	
}
