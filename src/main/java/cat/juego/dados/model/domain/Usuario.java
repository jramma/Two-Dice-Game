package cat.juego.dados.model.domain;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Usuario {
	private String nombre; // no se puede repetir
	private ArrayList <Partida> partidas;
	private int id_usuari;
	private String date;
	
	public Usuario(String nombre,int id_usuari) {
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
		this.nombre = nombre;
		this.partidas = new ArrayList<Partida>();
		this.id_usuari = id_usuari;
		this.date = dateFormat.format(Calendar.getInstance().getTime());
	}
	
	
}
