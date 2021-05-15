package br.ufrj.cos.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="photo_metada")
public class PhotoMetada implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="PHOTO_SEQUENCE_GENERATOR", sequenceName="PHOTO_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHOTO_SEQUENCE_GENERATOR")
	private Long id;
	private String path;
	
	public PhotoMetada(String path){
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
}
