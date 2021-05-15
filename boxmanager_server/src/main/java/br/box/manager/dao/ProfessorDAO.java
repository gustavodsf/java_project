package br.box.manager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.box.manager.model.Professor;


@Repository("professorDAO")
@Transactional
public class ProfessorDAO {

	@PersistenceContext
	EntityManager em;
	
	public  Integer save(Professor professor){
		em.persist(professor);
		return professor.getId();
	}
	
	public List<Professor> returnAll(){
		Query query  = em.createQuery("SELECT p FROM Professor p");
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		for(Object obj: query.getResultList()){
			professorList.add((Professor) obj);
		}
		return professorList;
	}

	public void edit(Professor professor) {
		em.merge(professor);
	}
	
	public Professor returnProfessorByNome(String nomeProfessor){
		try{
			Query query  = em.createQuery("SELECT p FROM Professor p WHERE p.nome = :nome");
			query.setParameter("nome", nomeProfessor);
			return (Professor) query.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
}
