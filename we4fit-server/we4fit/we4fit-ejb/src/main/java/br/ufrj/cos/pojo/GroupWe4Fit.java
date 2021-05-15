package br.ufrj.cos.pojo;

import java.io.Serializable;
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

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name="group_we4fit")
public class GroupWe4Fit implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="GROUP_SEQUENCE_GENERATOR", sequenceName="GROUP_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GROUP_SEQUENCE_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JsonIgnore
	private UserWe4Fit owner;
	
	@Column(unique=true)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="group")
	private List<UserWe4Fit> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserWe4Fit getOwner() {
		return owner;
	}

	public void setOwner(UserWe4Fit owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserWe4Fit> getUsers() {
		return users;
	}

	public void setUsers(List<UserWe4Fit> users) {
		this.users = users;
	}
}
