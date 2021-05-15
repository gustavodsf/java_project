package org.cgee.aquarius.dao;

import java.util.ArrayList;
import java.util.List;

import org.cgee.aquarius.model.CloudVO;
import org.cgee.aquarius.model.CooccurrenceVO;
import org.cgee.aquarius.model.TermDespesas;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TermDAODespesas {
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Object> retrieveAllCooccurrenceOfTerm(String term){
		String strQuery = "SELECT a.palavra, count(*) as qtd FROM dispendios_palavras a INNER JOIN dispendios_origem_has_palavra b ON a.idpalavras = b.idpalavra "
			     + "INNER JOIN dispendios_origem_has_palavra d ON b.idorigem = d.idorigem INNER JOIN dispendios_palavras c ON d.idpalavra = c.idpalavras"
			     + " WHERE c.palavra = \'"+term +"\' and a.palavra != \'"+term +"\' GROUP BY a.palavra ORDER BY qtd DESC";
		       
		Query query =  sessionFactory.getCurrentSession().createSQLQuery(strQuery);
		return query.list();
	}
	
	public Object []  getLabelOfStem(long l){
		//select max(qtd_word),t.word from term t where t.stem_id = 5195;
		Query query =  sessionFactory.getCurrentSession().createQuery("select max(t.qtdWord),t.word,t.id from TermDespesas t where t.stem.id = :idStem");
		query.setParameter("idStem",l);
		return (Object []) query.uniqueResult();
	}
	
	public List<CooccurrenceVO> getTermOfStem(long l,String stem){
		Query query =  sessionFactory.getCurrentSession().createQuery("select t from TermDespesas t where t.stem.id = :idStem");
		query.setParameter("idStem",l);
		List<CooccurrenceVO> cooList = new ArrayList<CooccurrenceVO>();
		for(Object obj :query.list()){
	    	TermDespesas term = (TermDespesas) obj;
	    	CooccurrenceVO coo = new CooccurrenceVO();
	    	coo.setName(term.getWord());
	    	coo.setParent(stem);
	    	coo.setOccurrence(0);
	    	cooList.add(coo);
	    }
		return cooList;
	}
	
	public List<CloudVO> getTopTermLimit200(){
		Query query =  sessionFactory.getCurrentSession().createQuery("SELECT t FROM TermDespesas t ORDER BY t.qtdWord DESC");
		query.setMaxResults(200);
		List<CloudVO> cloudList = new ArrayList<CloudVO>();
		for(Object obj: query.list()){
			TermDespesas term = (TermDespesas) obj;
			CloudVO cloud = new CloudVO();
			cloud.setCount(term.getQtdWord());
			cloud.setName(term.getWord());
			cloudList.add(cloud);
		}
		return cloudList;
	}
	
	public List<CloudVO> getNgramTermLimit200(String tipo){
		Query query =  sessionFactory.getCurrentSession().createQuery("SELECT t FROM TermDespesas t WHERE t.tipo = :tipo ORDER BY t.qtdWord DESC");
		query.setParameter("tipo",tipo);
		query.setMaxResults(200);
		List<CloudVO> cloudList = new ArrayList<CloudVO>();
		for(Object obj: query.list()){
			TermDespesas term = (TermDespesas) obj;
			CloudVO cloud = new CloudVO();
			cloud.setCount(term.getQtdWord());
			cloud.setName(term.getWord());
			cloudList.add(cloud);
		}
		return cloudList;
	}
	
	public List<CloudVO> findTermLikeByWord(String word){
		Query query =  sessionFactory.getCurrentSession().createQuery("SELECT t FROM TermDespesas t WHERE t.word like :word ORDER BY t.qtdWord DESC");
		query.setParameter("word","%"+word+"%");
		List<CloudVO> cloudList = new ArrayList<CloudVO>();
		for(Object obj: query.list()){
			TermDespesas term = (TermDespesas) obj;
			CloudVO cloud = new CloudVO();
			cloud.setCount(term.getQtdWord());
			cloud.setName(term.getWord());
			cloudList.add(cloud);
		}
		return cloudList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
