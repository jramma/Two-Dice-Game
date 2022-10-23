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
	
	
	
}
