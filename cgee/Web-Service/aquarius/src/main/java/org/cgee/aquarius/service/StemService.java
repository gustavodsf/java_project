package org.cgee.aquarius.service;

import java.util.List;

import org.cgee.aquarius.dao.StemDAO;
import org.cgee.aquarius.dao.TermDAO;
import org.cgee.aquarius.model.CloudVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("StemService")
@Transactional(readOnly = true)
public class StemService {
	
	@Autowired
	TermDAO termDAO;

	@Autowired
	StemDAO stemDAO;
	
	public List<CloudVO> getTop200Stem(String painel){
		return stemDAO.retrieveTop200Stem(painel);
	}
	
	public long getStemByRadical(String radical,String painel){
		return stemDAO.findStemByRadical(radical,painel);
	}
	
	public TermDAO getTermDAO() {
		return termDAO;
	}

	public void setTermDAO(TermDAO termDAO) {
		this.termDAO = termDAO;
	}

	public StemDAO getStemDAO() {
		return stemDAO;
	}

	public void setStemDAO(StemDAO stemDAO) {
		this.stemDAO = stemDAO;
	}
}
