package br.ufrj.cos.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.cos.pojo.Photo;

@Stateless(name="photoMetadaEntityBean")
public class PhotoMetadaEntityBean implements PhotoMetadaEntityBeanInterface {
	
	@PersistenceContext(unitName="we4fit")
	EntityManager em;
	
	@Override
	public void save(Photo file){
		em.persist(file);
	}
}
