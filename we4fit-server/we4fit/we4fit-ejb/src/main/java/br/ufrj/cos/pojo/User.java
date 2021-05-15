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


@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="USER_SEQUENCE_GENERATOR", sequenceName="USER_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQUENCE_GENERATOR")
	private Long id;
	
    @OneToMany(mappedBy="user")
    private List<Photo> photos;
    
    @Column(name="id_facebook")
    private String idFacebook;
    
    @Column(name="admission_date")
    private Calendar admissionDate;
    
    private String height;
    
    @ManyToOne
    private Group group;
    
    @OneToMany(mappedBy="user")
    private List<Weight> weights;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
