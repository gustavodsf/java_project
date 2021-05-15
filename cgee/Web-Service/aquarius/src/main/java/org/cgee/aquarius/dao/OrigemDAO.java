package org.cgee.aquarius.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.cgee.aquarius.model.OrigemProducao;
import org.cgee.aquarius.model.OrigemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrigemDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	OrigemDAOBolsa origemDAOBolsa;
	
	@Autowired
	OrigemDAOConvenio origemDAOConvenio;
	
	@Autowired
	OrigemDAODespesas origemDAODespesas;
	
	@Autowired
	OrigemDAOFundoSetorial origemDAOFundoSetorial;
	
	@Autowired
	OrigemDAOMidiaSocial origemDAOMidiaSocial;
	
	@Autowired
	OrigemDAOProducao origemDAOProducao;
	
	
	//select * from origem o where o.id in (select origem.origem_id from term_has_origem origem JOIN term tr on tr.id = origem.termo_id where tr.word = 'acai' and  origem.origem_id in (select tho.origem_id from term_has_origem tho JOIN term t on t.id = tho.termo_id where t.word = 'polpa'));
	
	public List<OrigemVO> findOrigemByWords(String wordPrincipal,String word,String painel){
		List<Object> objList = null;
		if("bolsa".equals(painel))
			objList =  origemDAOBolsa.findOrigemByWords(wordPrincipal,word);
		else if("convenio".equals(painel))
			objList =  origemDAOConvenio.findOrigemByWords(wordPrincipal,word);
		else if("despesas".equals(painel))
			objList =  origemDAODespesas.findOrigemByWords(wordPrincipal,word);
		else if("fundo_setorial".equals(painel))
			objList =  origemDAOFundoSetorial.findOrigemByWords(wordPrincipal,word);
		else if("midia_social".equals(painel))
			objList =  origemDAOMidiaSocial.findOrigemByWords(wordPrincipal,word);
		else if("producao".equals(painel))
			objList =  origemDAOProducao.findOrigemByWords(wordPrincipal,word);
		
		List<OrigemVO> origemList = new ArrayList<OrigemVO>();
		for(Object obj: objList){
			OrigemVO origem = new OrigemVO();
			origem.setName(obj.toString());
			origem.setWord1(wordPrincipal);
			origem.setWord2(word);
			origemList.add(origem);
		}
		return origemList;
	}


	public OrigemDAOBolsa getOrigemDAOBolsa() {
		return origemDAOBolsa;
	}


	public void setOrigemDAOBolsa(OrigemDAOBolsa origemDAOBolsa) {
		this.origemDAOBolsa = origemDAOBolsa;
	}


	public OrigemDAOConvenio getOrigemDAOConvenio() {
		return origemDAOConvenio;
	}


	public void setOrigemDAOConvenio(OrigemDAOConvenio origemDAOConvenio) {
		this.origemDAOConvenio = origemDAOConvenio;
	}


	public OrigemDAODespesas getOrigemDAODespesas() {
		return origemDAODespesas;
	}


	public void setOrigemDAODespesas(OrigemDAODespesas origemDAODespesas) {
		this.origemDAODespesas = origemDAODespesas;
	}


	public OrigemDAOFundoSetorial getOrigemDAOFundoSetorial() {
		return origemDAOFundoSetorial;
	}


	public void setOrigemDAOFundoSetorial(
			OrigemDAOFundoSetorial origemDAOFundoSetorial) {
		this.origemDAOFundoSetorial = origemDAOFundoSetorial;
	}


	public OrigemDAOMidiaSocial getOrigemDAOMidiaSocial() {
		return origemDAOMidiaSocial;
	}


	public void setOrigemDAOMidiaSocial(OrigemDAOMidiaSocial origemDAOMidiaSocial) {
		this.origemDAOMidiaSocial = origemDAOMidiaSocial;
	}


	public OrigemDAOProducao getOrigemDAOProducao() {
		return origemDAOProducao;
	}


	public void setOrigemDAOProducao(OrigemDAOProducao origemDAOProducao) {
		this.origemDAOProducao = origemDAOProducao;
	}

	
}
