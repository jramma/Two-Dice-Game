package cat.juego.dados.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Builder
@Entity
@Table(name = "partidas")
public class Partida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPartida;
	
	@Column(name = "usuario", nullable = false)	
	private String usuario;
	
	@Column(name = "dado1", nullable = false)	
	private int dado1;
	
	@Column(name = "dado2", nullable = false)	
	private int dado2;
	
	@Column(name = "resultado", nullable = false)	
	private String resultado;
	
	
	public Partida(String usuario, int dado1, int dado2) {
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

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getDado1() {
		return dado1;
	}
	public void setDado1(int dado1) {
		this.dado1 = dado1;
	}
	public int getDado2() {
		return dado2;
	}
	public void setDado2(int dado2) {
		this.dado2 = dado2;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	@Override
	public String toString() {
		return "Partida [usuario=" + usuario + ", dado1=" + dado1 + ", dado2=" + dado2 + ", resultado=" + resultado
				+ "]";
	}
	
	
	
}
