package cat.juego.dados.model.domain;

import java.io.Serializable;

public class DadoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numero;

	public DadoDto() {
		do {
			numero = (int) (Math.random() * 6);
		} while (numero == 0);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
