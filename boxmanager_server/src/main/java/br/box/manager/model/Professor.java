package br.box.manager.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Professor {

	@Id
	@Basic(optional = false)
	@Column(name = "id_professor")
	@GeneratedValue(generator = "id_professor", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "id_professor", sequenceName = "professor_id_seq")
	private int id;
	
	@Column(length=100)
	private String nome;
	
	@Column(name="hora_aula",length=10)
	private String horaAula;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHoraAula() {
		return horaAula;
	}

	public void setHoraAula(String horaAula) {
		this.horaAula = horaAula;
	}
	
	
}
