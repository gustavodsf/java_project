package org.cgee.aquarius.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="midia_social_palavras")
public class TermMidiaSocial implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="idpalavras")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="idstem")
	private StemMidiaSocial stem;
	
	@Column(name="palavra")
	private String word;
	
	@Column(name="qtd_word")
	private int qtdWord;
	
	@Column(name="tipo")
	private String tipo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StemMidiaSocial getStem() {
		return stem;
	}

	public void setStem(StemMidiaSocial stem) {
		this.stem = stem;
	}


	public int getQtdWord() {
		return qtdWord;
	}

	public void setQtdWord(int qtdWord) {
		this.qtdWord = qtdWord;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
