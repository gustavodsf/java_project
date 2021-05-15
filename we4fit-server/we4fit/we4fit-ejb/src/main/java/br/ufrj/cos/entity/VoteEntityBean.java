package br.ufrj.cos.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.cos.pojo.Vote;

@Stateless(name="voteEntityBean")
public class VoteEntityBean implements VoteEntityBeanInterface {

	@PersistenceContext(unitName="we4fit")
	EntityManager em;
	
	@Override
	public void save(Vote vote) {
		// TODO Auto-generated method stub
		em.persist(vote);
	}
	
}
