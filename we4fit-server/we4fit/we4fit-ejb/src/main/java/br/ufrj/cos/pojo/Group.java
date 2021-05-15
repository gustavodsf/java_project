package br.ufrj.cos.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Group implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="GROUP_SEQUENCE_GENERATOR", sequenceName="GROUP_SEQUENCE", initialValue=1, allocationSize=1)
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GROUP_SEQUENCE_GENERATOR")
	private Long id;
	
	private String name;
	
	private User owner;
	
	@OneToMany(mappedBy="group")
	private List<User> usersGroup;

	public List<User> getUsersGroup() {
		return usersGroup;
	}

	public void setUsersGroup(List<User> usersGroup) {
		this.usersGroup = usersGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

    
}
