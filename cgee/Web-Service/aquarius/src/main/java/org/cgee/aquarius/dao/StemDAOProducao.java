package org.cgee.aquarius.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.cgee.aquarius.model.CloudVO;
import org.cgee.aquarius.model.StemMidiaSocial;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StemDAOProducao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<CloudVO> retrieveTop200Stem(){
		
		Query query =  sessionFactory.getCurrentSession().createQuery("SELECT s FROM StemProducao s ORDER BY s.qtdStem DESC");
		query.setMaxResults(200);
		ArrayList<CloudVO> cloudList = new ArrayList<CloudVO>();
		for(Object obj:query.list()){
			StemMidiaSocial stem = (StemMidiaSocial) obj;
			CloudVO cloud = new CloudVO();
			cloud.setCount(stem.getQtdStem());
			cloud.setName(stem.getStem());
			cloudList.add(cloud);
		}
		return cloudList;
	}
	
	public long findStemByRadical(String radical){
		
		Query query =  sessionFactory.getCurrentSession().createQuery("SELECT s FROM StemProducao s WHERE  s.stem = :stem");
		query.setParameter("stem", radical);
		StemMidiaSocial stem = (StemMidiaSocial) query.uniqueResult();
		return stem.getId();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
