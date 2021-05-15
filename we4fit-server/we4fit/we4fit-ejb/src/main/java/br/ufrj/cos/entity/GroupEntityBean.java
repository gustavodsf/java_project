package br.ufrj.cos.entity;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import br.ufrj.cos.pojo.GroupWe4Fit;


@Stateless(name="groupEntityBean")
public class GroupEntityBean implements GroupEntityBeanInterface {
	
	@PersistenceContext(unitName="we4fit", type=PersistenceContextType.EXTENDED)
	EntityManager em;
	
	@Override
	public void save(GroupWe4Fit group) {
		// TODO Auto-generated method stub
		em.persist(group);
	}

	@Override
	public void edit(GroupWe4Fit group) {
		// TODO Auto-generated method stub
		em.merge(group);
	}

	@Override
	public void delete(GroupWe4Fit group) {
		// TODO Auto-generated method stub
		em.remove(group);
	}

	@Override
	public List<GroupWe4Fit> retieveAllByLikeNome(String name) {
		Query query = em.createQuery("SELECT g FROM GroupWe4Fit g WHERE lower(g.name) like :name");
		query.setParameter("name", name.toLowerCase());
		
		List<?> objs = query.getResultList();
		List<GroupWe4Fit> groups = new ArrayList<GroupWe4Fit>();
		
		for(Object obj: objs){
			groups.add((GroupWe4Fit) obj);
		}
		return groups;
	}

	@Override
	public List<GroupWe4Fit> retieveAll() {
		Query query = em.createQuery("SELECT g FROM GroupWe4Fit");
 		
		List<?> objs = query.getResultList();
		List<GroupWe4Fit> groups = new ArrayList<GroupWe4Fit>();
		
		for(Object obj: objs){
			groups.add((GroupWe4Fit) obj);
		}
		return groups;
	}

	@Override
	public GroupWe4Fit findGroupById(long  id) {
		Query query = em.createQuery("SELECT g FROM GroupWe4Fit g WHERE g.id = :id");
		query.setParameter("id", id);
		return (GroupWe4Fit) query.getSingleResult();
	}

	@Override
	public GroupWe4Fit findGroupByName(String name) {
		
		Query query = em.createQuery("SELECT g FROM GroupWe4Fit g WHERE lower(g.name) = :name");
		query.setParameter("name", name.toLowerCase());
		try{
			return (GroupWe4Fit) query.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
	
}
