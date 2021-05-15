package br.ufrj.cos.pojo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="user_we4fit")
public class UserWe4Fit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="USER_SEQUENCE_GENERATOR", sequenceName="USER_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQUENCE_GENERATOR")
	private Long id;

	@ManyToOne
	private GroupWe4Fit group;
	
	private String idFacebook;
	
	@OneToMany(mappedBy="user")
	private List<Weight> weights;
	
	private String height;
	
	@Column(name="admission_date")
	private Calendar admissionDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroupWe4Fit getGroup() {
		return group;
	}

	public void setGroup(GroupWe4Fit group) {
		this.group = group;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public List<Weight> getWeights() {
		return weights;
	}

	public void setWeights(List<Weight> weights) {
		this.weights = weights;
	}
	
	public void addWeights(Weight weights) {
		this.weights.add(weights);
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Calendar getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Calendar admissionDate) {
		this.admissionDate = admissionDate;
	}
	
}
