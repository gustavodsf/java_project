package org.cgee.aquarius.service;

import java.util.List;

import org.cgee.aquarius.dao.OrigemDAO;
import org.cgee.aquarius.dao.StemDAO;
import org.cgee.aquarius.dao.TermDAO;
import org.cgee.aquarius.model.CloudVO;
import org.cgee.aquarius.model.CooccurrenceVO;
import org.cgee.aquarius.model.TermProducao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TermService")
@Transactional(readOnly = true)
public class TermService {
	
	@Autowired
	TermDAO termDAO;
	
	@Autowired
	StemDAO stemDAO;
	
	@Autowired
	OrigemDAO origemDAO;

	public List<CooccurrenceVO> getAllCooccurrence(String termCalcCooccurrence, int qtd,String painel){
		List<CooccurrenceVO> cooList = termDAO.retrieveAllCooccurrenceOfTerm(termCalcCooccurrence,qtd,painel);
		return cooList;
	}
	
	public List<CloudVO> getTop200NTerms(String painel){
		return termDAO.retrieveTop200Term(painel);
	}
	
	public List<CloudVO> getTop200NGramTerms(String tipo,String painel){
		return termDAO.getNgramTermLimit200(tipo,painel);
	}
	
	public List<CooccurrenceVO> getTermsOfStem(String stemStr,String painel){
		long stemId = stemDAO.findStemByRadical(stemStr,painel);
		return termDAO.getTermOfStem(stemId, stemStr,painel);
	}
	
	public TermProducao getLabelOfStem(long idStem,String painel){
		return termDAO.getLabelOfStem(idStem,painel);
	}
	
	public List<CloudVO> findTermByLike(String word,String painel){
		return termDAO.findTermLikeByWord(word,painel);
	}
	
	public TermDAO getTermDAO() {
		return termDAO;
	}

	public void setTermDAO(TermDAO termDAO) {
		this.termDAO = termDAO;
	}

	public OrigemDAO getOrigemDAO() {
		return origemDAO;
	}

	public void setOrigemDAO(OrigemDAO origemDAO) {
		this.origemDAO = origemDAO;
	}

	public StemDAO getStemDAO() {
		return stemDAO;
	}

	public void setStemDAO(StemDAO stemDAO) {
		this.stemDAO = stemDAO;
	}
}
