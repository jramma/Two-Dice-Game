package cat.juego.dados.model.domain;


import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuaris", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", nullable = false)
	private String nombre; // no se puede repetir

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "ranquing", nullable = false)
	private double ranquing;
	
	
	@Column(name = "date")
	private String date;
	
	@ManyToMany(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(
			name ="usuarios_roles",
			joinColumns=@JoinColumn(
					name="usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="rol_id",referencedColumnName = "id")
			)	
	private Collection<Rol> roles;
	


	

	public Usuario(String nombre, String password, double ranquing, ArrayList<Partida> partidas, String date,
			Collection<Rol> roles) {
		this.nombre = nombre;
		this.password = password;
		this.ranquing = ranquing;
		this.date = date;
		this.roles = roles;
	}
	

}
