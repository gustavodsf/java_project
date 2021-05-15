package org.cgee.aquarius.dao;

import java.util.ArrayList;
import java.util.List;

import org.cgee.aquarius.model.CloudVO;
import org.cgee.aquarius.model.CooccurrenceVO;
import org.cgee.aquarius.model.TermProducao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TermDAO {
	
	@Autowired
	TermDAOBolsa termDAOBolsa;
	
	@Autowired
	TermDAOConvenio termDAOConvenio;
	
	@Autowired
	TermDAODespesas termDAODespesas;
	
	@Autowired
	TermDAOFundoSetorial termDAOFundoSetorial;
	
	@Autowired
	TermDAOMidiaSocial termDAOMidiaSocial;
	
	@Autowired
	TermDAOProducao termDAOProducao;
	
	public  List<CooccurrenceVO> retrieveAllCooccurrenceOfTerm(String term,int qtd,String painel){
		List<Object> objList = null;
		
		if("bolsa".equals(painel))
			objList =  termDAOBolsa.retrieveAllCooccurrenceOfTerm(term);
		else if("convenio".equals(painel))
			objList =  termDAOConvenio.retrieveAllCooccurrenceOfTerm(term);
		else if("despesas".equals(painel))
			objList =  termDAODespesas.retrieveAllCooccurrenceOfTerm(term);
		else if("fundo_setorial".equals(painel))
			objList =  termDAOFundoSetorial.retrieveAllCooccurrenceOfTerm(term);
		else if("midia_social".equals(painel))
			objList =  termDAOMidiaSocial.retrieveAllCooccurrenceOfTerm(term);
		else if("producao".equals(painel))
			objList =  termDAOProducao.retrieveAllCooccurrenceOfTerm(term);
		
		List<CooccurrenceVO> cooList = new ArrayList<CooccurrenceVO>();
	    for(Object obj :objList){
	    	Object [] object = (Object []) obj;
	    	CooccurrenceVO coo = new CooccurrenceVO();
	    	coo.setName(object[0].toString());
	    	coo.setParent(term);
	    	coo.setOccurrence(Integer.parseInt(object[1].toString()));
	    	cooList.add(coo);
	    }
		return cooList;
	}
	
	public TermProducao getLabelOfStem(long l,String painel){
		Object [] object = null;	
		
		if("bolsa".equals(painel))
			object =  termDAOBolsa.getLabelOfStem(l);
		else if("convenio".equals(painel))
			object =  termDAOConvenio.getLabelOfStem(l);
		else if("despesas".equals(painel))
			object =  termDAODespesas.getLabelOfStem(l);
		else if("fundo_setorial".equals(painel))
			object =  termDAOFundoSetorial.getLabelOfStem(l);
		else if("midia_social".equals(painel))
			object =  termDAOMidiaSocial.getLabelOfStem(l);
		else if("producao".equals(painel))
			object =  termDAOProducao.getLabelOfStem(l);
		
		if(object[0] != null){
			TermProducao term = new TermProducao();
			term.setQtdWord(Integer.parseInt(object[0].toString()));
			term.setWord(object[1].toString());
			term.setId(Integer.parseInt(object[2].toString()));
			return term;
		}
		else{
			return null;
		}
	}
	
	public List<CooccurrenceVO> getTermOfStem(long l,String stem,String painel){
		if("bolsa".equals(painel))
			return termDAOBolsa.getTermOfStem(l,stem);
		else if("convenio".equals(painel))
			return termDAOConvenio.getTermOfStem(l,stem);
		else if("despesas".equals(painel))
			return termDAODespesas.getTermOfStem(l,stem);
		else if("fundo_setorial".equals(painel))
			return termDAOFundoSetorial.getTermOfStem(l,stem);
		else if("midia_social".equals(painel))
			return termDAOMidiaSocial.getTermOfStem(l,stem);
		else if("producao".equals(painel))
			return termDAOProducao.getTermOfStem(l, stem);
		return null;
	}
	
	public List<CloudVO> retrieveTop200Term(String painel){
				
		if("bolsa".equals(painel))
			return termDAOBolsa.getTopTermLimit200();
		else if("convenio".equals(painel))
			return termDAOConvenio.getTopTermLimit200();
		else if("despesas".equals(painel))
			return  termDAODespesas.getTopTermLimit200();
		else if("fundo_setorial".equals(painel))
			return  termDAOFundoSetorial.getTopTermLimit200();
		else if("midia_social".equals(painel))
			return  termDAOMidiaSocial.getTopTermLimit200();
		else if("producao".equals(painel))
			return termDAOProducao.getTopTermLimit200();
		return null;
	}
	
	
	public List<CloudVO> getNgramTermLimit200(String tipo,String painel){		
		if("bolsa".equals(painel))
			return termDAOBolsa.getNgramTermLimit200(tipo);
		else if("convenio".equals(painel))
			return termDAOConvenio.getNgramTermLimit200(tipo);
		else if("despesas".equals(painel))
			return termDAODespesas.getNgramTermLimit200(tipo);
		else if("fundo_setorial".equals(painel))
			return termDAOFundoSetorial.getNgramTermLimit200(tipo);
		else if("midia_social".equals(painel))
			return termDAOMidiaSocial.getNgramTermLimit200(tipo);
		else if("producao".equals(painel))
			return termDAOProducao.getNgramTermLimit200(tipo);
		return  null;

	}
	
	public List<CloudVO> findTermLikeByWord(String word,String painel){
		
		if("bolsa".equals(painel))
			return termDAOBolsa.findTermLikeByWord(word);
		else if("convenio".equals(painel))
			return termDAOConvenio.findTermLikeByWord(word);
		else if("despesas".equals(painel))
			return termDAODespesas.findTermLikeByWord(word);
		else if("fundo_setorial".equals(painel))
			return termDAOFundoSetorial.findTermLikeByWord(word);
		else if("midia_social".equals(painel))
			return termDAOMidiaSocial.findTermLikeByWord(word);
		else if("producao".equals(painel))
			return termDAOProducao.findTermLikeByWord(word);
		return null;
	}

	public TermDAOBolsa getTermDAOBolsa() {
		return termDAOBolsa;
	}

	public void setTermDAOBolsa(TermDAOBolsa termDAOBolsa) {
		this.termDAOBolsa = termDAOBolsa;
	}

	public TermDAOConvenio getTermDAOConvenio() {
		return termDAOConvenio;
	}

	public void setTermDAOConvenio(TermDAOConvenio termDAOConvenio) {
		this.termDAOConvenio = termDAOConvenio;
	}

	public TermDAODespesas getTermDAODespesas() {
		return termDAODespesas;
	}

	public void setTermDAODespesas(TermDAODespesas termDAODespesas) {
		this.termDAODespesas = termDAODespesas;
	}

	public TermDAOFundoSetorial getTermDAOFundoSetorial() {
		return termDAOFundoSetorial;
	}

	public void setTermDAOFundoSetorial(TermDAOFundoSetorial termDAOFundoSetorial) {
		this.termDAOFundoSetorial = termDAOFundoSetorial;
	}

	public TermDAOMidiaSocial getTermDAOMidiaSocial() {
		return termDAOMidiaSocial;
	}

	public void setTermDAOMidiaSocial(TermDAOMidiaSocial termDAOMidiaSocial) {
		this.termDAOMidiaSocial = termDAOMidiaSocial;
	}

	public TermDAOProducao getTermDAOProducao() {
		return termDAOProducao;
	}

	public void setTermDAOProducao(TermDAOProducao termDAOProducao) {
		this.termDAOProducao = termDAOProducao;
	}


}
