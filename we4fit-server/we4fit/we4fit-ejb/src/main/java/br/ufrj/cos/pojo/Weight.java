package br.ufrj.cos.pojo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Weight implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="WEIGHT_SEQUENCE_GENERATOR", sequenceName="WEIGHT_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEIGHT_SEQUENCE_GENERATOR")
	private Long id;

	@JsonIgnore
	@ManyToOne
	private UserWe4Fit user;
	
	private String weight;
	
	@Column(name="insertion_date")
	private Calendar insertionDate;
	
	public UserWe4Fit getUser() {
		return user;
	}

	public void setUser(UserWe4Fit user) {
		this.user = user;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Calendar insertionDate) {
		this.insertionDate = insertionDate;
	}
}
