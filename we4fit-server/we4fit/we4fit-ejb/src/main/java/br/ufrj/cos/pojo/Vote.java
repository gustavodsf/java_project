package br.ufrj.cos.pojo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Vote implements Serializable{
private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="VOTE_SEQUENCE_GENERATOR", sequenceName="VOTE_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VOTE_SEQUENCE_GENERATOR")
	private Long id;
	
	@ManyToOne
	private UserWe4Fit userWe4Fit;
	
	@ManyToOne
	private Photo photo;
	
	private String grade;
	
	private Calendar date;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserWe4Fit getUserWe4Fit() {
		return userWe4Fit;
	}

	public void setUserWe4Fit(UserWe4Fit userWe4Fit) {
		this.userWe4Fit = userWe4Fit;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
}
