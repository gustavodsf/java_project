package org.cgee.aquarius.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="convenio_radical")
public class StemConvenio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="idstem")
	private long id;
	
	private String stem;
	
	@Column(name="qtd_stem")
	private int qtdStem;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public int getQtdStem() {
		return qtdStem;
	}

	public void setQtdStem(int qtdStem) {
		this.qtdStem = qtdStem;
	}
}
