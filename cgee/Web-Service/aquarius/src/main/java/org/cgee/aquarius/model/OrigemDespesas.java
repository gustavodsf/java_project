package org.cgee.aquarius.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dispendios_origem")
public class OrigemDespesas implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="idorigem")
	private long id;
	
	@Column(name="titulo",columnDefinition="TEXT")
	private String texto;
	
	@Column(name="id_original")
	private long idOriginal;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public long getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(long idOriginal) {
		this.idOriginal = idOriginal;
	}
}
