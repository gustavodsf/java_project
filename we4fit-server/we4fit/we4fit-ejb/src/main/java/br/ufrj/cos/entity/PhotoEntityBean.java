package br.ufrj.cos.entity;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.cos.pojo.Photo;
import br.ufrj.cos.pojo.UserWe4Fit;

@Stateless(name="photoEntityBean")
public class PhotoEntityBean implements PhotoEntityBeanInterface {
	
	@PersistenceContext(unitName="we4fit")
	EntityManager em;
	
	@Override
	public void save(Photo file){
		em.persist(file);
	}
	
	@Override
	public List<Photo> retrieveAllPhotoNotVotedByUSer(UserWe4Fit user){
		Query query = em.createQuery("SELECT p FROM Photo p WHERE p.user.id != :userID AND p.id NOT IN (SELECT v.photo.id FROM Vote v WHERE v.userWe4Fit.id = :userID) AND p.inappropriate ='FALSE' AND p.inThPoll = 'TRUE'");
		query.setParameter("userID", user.getId());
		
		List<Photo> photos = new ArrayList<Photo>();
		
		for(Object obj: query.getResultList()){
			photos.add((Photo) obj);
		}
		
		return photos;
	}
	
	@Override
	public void setPhotoIsInappropriate(Long id){
		Query query = em.createQuery("UPDATE Photo p SET p.inappropriate = :true WHERE p.id = :id");
		query.setParameter("true", true);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public Photo findPhotoById(Long id){
		Query query = em.createQuery("SELECT p FROM Photo p WHERE p.id = :id");
		query.setParameter("id", id);
		return (Photo) query.getSingleResult();
	}
}
