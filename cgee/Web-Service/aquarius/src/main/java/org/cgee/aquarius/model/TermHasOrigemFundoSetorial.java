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
@Table(name="fundo_setorial_origem_has_palavra")
public class TermHasOrigemFundoSetorial implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="idorigem_has_palavra")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="idpalavra")
	private TermProducao term;
	
	@ManyToOne
	@JoinColumn(name="idorigem")
	private OrigemProducao origem;
	
	private String word;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TermProducao getTerm() {
		return term;
	}

	public void setTerm(TermProducao term) {
		this.term = term;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public OrigemProducao getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemProducao origem) {
		this.origem = origem;
	}
}
