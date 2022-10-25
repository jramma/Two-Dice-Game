package cat.juego.dados.model.domain;

public class Partida {
	private String usuario;
	private int dado1;
	private int dado2;
	private String resultado;
	public Partida(String usuario, int dado1, int dado2) {

		this.usuario = usuario;
		this.dado1 = dado1;
		this.dado2 = dado2;
		if(dado1+dado2==7) {
			resultado = "victory";
		}else {
			resultado = "you lose";
		}
		this.resultado = resultado;
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
