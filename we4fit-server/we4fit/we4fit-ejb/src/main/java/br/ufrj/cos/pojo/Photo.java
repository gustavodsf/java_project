package br.ufrj.cos.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="PHOTO_SEQUENCE_GENERATOR", sequenceName="PHOTO_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHOTO_SEQUENCE_GENERATOR")
	private Long id;
	
	@ManyToOne
	private UserWe4Fit user;
	
	@Column(name="user_grade")
	private String userGrade;
	
	private String path;
	
	private boolean inappropriate;
	
	private boolean inThPoll;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserWe4Fit getUser() {
		return user;
	}

	public void setUser(UserWe4Fit user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isInappropriate() {
		return inappropriate;
	}

	public void setInappropriate(boolean inappropriate) {
		this.inappropriate = inappropriate;
	}

	public boolean isInThPoll() {
		return inThPoll;
	}

	public void setInThPoll(boolean inThPoll) {
		this.inThPoll = inThPoll;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
}
