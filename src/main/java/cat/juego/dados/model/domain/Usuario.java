package cat.juego.dados.model.domain;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuaris")
public class Usuario  {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuari;
	
	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre; // no se puede repetir
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "ranquing", nullable = false)
	private double ranquing;
	

	private ArrayList <Partida> partidas;
	private String date;
	
	public Usuario() {
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
		this.date = dateFormat.format(Calendar.getInstance().getTime());
		this.ranquing = caluleteRanquing();
		this.partidas = new ArrayList<>();

	}
	
	public Usuario(String nombre, String password) {
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
		this.nombre = nombre;
		this.password = password;
		this.ranquing = caluleteRanquing();
		this.partidas = new ArrayList<>();
		this.date = dateFormat.format(Calendar.getInstance().getTime());
	}

	
	public double caluleteRanquing() {
		int victorias = 0;
		int derrotas = 0;
		if(!(partidas==null)) {
		for (int j = 0; j < partidas.size(); j++) {
			if (partidas.get(j).getResultado().equalsIgnoreCase("victory")) {
				victorias ++;
			} else {
				derrotas ++;
			}
		}
		}
		double media;
		if(!(victorias== 0 && derrotas == 0)) {
		 media = victorias/ (victorias+derrotas);	
		}
		
		media = 0;
		return media;
	}



	
	
	
}
