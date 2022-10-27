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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuaris")
public class Usuario {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuari;
	
	@Column(name = "nombre", nullable = false)
	private String nombre; // no se puede repetir
	
	@Column(name = "password", nullable = false)
	private String password;
	
	private ArrayList <Partida> partidas;
	private String date;
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String password) {
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
		this.nombre = nombre;
		this.password = password;
		this.partidas = new ArrayList<Partida>();
		this.date = dateFormat.format(Calendar.getInstance().getTime());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<Partida> partidas) {
		this.partidas = partidas;
	}

	public int getId_usuari() {
		return id_usuari;
	}

	public void setId_usuari(int id_usuari) {
		this.id_usuari = id_usuari;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getRanquing() {
		int victorias = 0;
		int derrotas = 0;
		for (int j = 0; j < partidas.size(); j++) {
			if (partidas.get(j).getResultado().equalsIgnoreCase("victory")) {
				victorias ++;
			} else {
				derrotas ++;
			}
		}
		double media;
		if(!(victorias== 0 && derrotas == 0)) {
		 media = victorias/ (victorias+derrotas);	
		}
		
		media = 0;
		return media;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
