package org.cgee.aquarius.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.cgee.aquarius.model.OrigemProducao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrigemDAOFundoSetorial implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	SessionFactory sessionFactory;
	
	//select * from origem o where o.id in (select origem.origem_id from term_has_origem origem JOIN term tr on tr.id = origem.termo_id where tr.word = 'acai' and  origem.origem_id in (select tho.origem_id from term_has_origem tho JOIN term t on t.id = tho.termo_id where t.word = 'polpa'));
	
	public List<Object> findOrigemByWords(String wordPrincipal,String word){
		String strQuery = "SELECT o.titulo FROM fundo_setorial_palavras a INNER JOIN fundo_setorial_origem_has_palavra b ON a.idpalavras = b.idpalavra INNER JOIN fundo_setorial_origem_has_palavra d ON b.idorigem = d.idorigem "+
						  "INNER JOIN fundo_setorial_palavras c ON d.idpalavra = c.idpalavras INNER JOIN fundo_setorial_origem o ON o.idorigem = b.idorigem WHERE "+
						  "c.palavra = \'"+ wordPrincipal +"\' and a.palavra = \'"+word+"\'";



		
		Query query =  sessionFactory.getCurrentSession().createSQLQuery(strQuery);
		return query.list();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
