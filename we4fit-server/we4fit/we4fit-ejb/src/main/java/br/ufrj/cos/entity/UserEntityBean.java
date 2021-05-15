package br.ufrj.cos.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import br.ufrj.cos.pojo.UserWe4Fit;
import br.ufrj.cos.pojo.Weight;

@Stateless(name="userEntityBean")
public class UserEntityBean implements UserEntityBeanInterface{
	
	@PersistenceContext(unitName="we4fit", type=PersistenceContextType.EXTENDED)
	EntityManager em;
	
	@Override
	public void save(UserWe4Fit user) {
		em.persist(user);
	}
	
	@Override
	public UserWe4Fit findUserByFacebookId(String idFacebook){
		Query query = em.createQuery("SELECT u FROM UserWe4Fit u WHERE u.idFacebook = :idFacebook");
		query.setParameter("idFacebook", idFacebook);
		try{
			return (UserWe4Fit) query.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
	
	@Override
	public void editUser(UserWe4Fit user){
		for(Weight w: user.getWeights()){
			em.merge(w);
		}
		em.merge(user);
	}
}
