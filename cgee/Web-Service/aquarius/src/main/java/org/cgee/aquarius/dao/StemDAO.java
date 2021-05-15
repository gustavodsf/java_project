package org.cgee.aquarius.dao;

import java.io.Serializable;
import java.util.List;

import org.cgee.aquarius.model.CloudVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StemDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	StemDAOBolsa stemDAOBolsa;
	
	@Autowired
	StemDAOConvenio stemDAOConvenio;
	
	@Autowired
	StemDAODespesas stemDAODespesas;
	
	@Autowired
	StemDAOFundoSetorial stemDAOFundoSetorial;
	
	@Autowired
	StemDAOMidiaSocial stemDAOMidiaSocial;
	
	@Autowired
	StemDAOProducao stemDAOProducao;
	
	
	public List<CloudVO> retrieveTop200Stem(String painel){
			
		if("bolsa".equals(painel))
			return  stemDAOBolsa.retrieveTop200Stem();
		else if("convenio".equals(painel))
			return stemDAOConvenio.retrieveTop200Stem();
		else if("despesas".equals(painel))
			return stemDAODespesas.retrieveTop200Stem();
		else if("fundo_setorial".equals(painel))
			return stemDAOFundoSetorial.retrieveTop200Stem();
		else if("midia_social".equals(painel))
			return stemDAOMidiaSocial.retrieveTop200Stem();
		else if("producao".equals(painel))
			return stemDAOProducao.retrieveTop200Stem();
		return  null;
	
		
	}
	
	public long findStemByRadical(String radical,String painel){
		
		if("bolsa".equals(painel))
			return stemDAOBolsa.findStemByRadical(radical);
		else if("convenio".equals(painel))
			return stemDAOConvenio.findStemByRadical(radical);
		else if("despesas".equals(painel))
			return stemDAODespesas.findStemByRadical(radical);
		else if("fundo_setorial".equals(painel))
			return stemDAOFundoSetorial.findStemByRadical(radical);
		else if("midia_social".equals(painel))
			return stemDAOMidiaSocial.findStemByRadical(radical);
		else if("producao".equals(painel))
			return stemDAOProducao.findStemByRadical(radical);
		return -1;
	}

	public StemDAOBolsa getStemDAOBolsa() {
		return stemDAOBolsa;
	}

	public void setStemDAOBolsa(StemDAOBolsa stemDAOBolsa) {
		this.stemDAOBolsa = stemDAOBolsa;
	}

	public StemDAOConvenio getStemDAOConvenio() {
		return stemDAOConvenio;
	}

	public void setStemDAOConvenio(StemDAOConvenio stemDAOConvenio) {
		this.stemDAOConvenio = stemDAOConvenio;
	}

	public StemDAODespesas getStemDAODespesas() {
		return stemDAODespesas;
	}

	public void setStemDAODespesas(StemDAODespesas stemDAODespesas) {
		this.stemDAODespesas = stemDAODespesas;
	}

	public StemDAOFundoSetorial getStemDAOFundoSetorial() {
		return stemDAOFundoSetorial;
	}

	public void setStemDAOFundoSetorial(StemDAOFundoSetorial stemDAOFundoSetorial) {
		this.stemDAOFundoSetorial = stemDAOFundoSetorial;
	}

	public StemDAOMidiaSocial getStemDAOMidiaSocial() {
		return stemDAOMidiaSocial;
	}

	public void setStemDAOMidiaSocial(StemDAOMidiaSocial stemDAOMidiaSocial) {
		this.stemDAOMidiaSocial = stemDAOMidiaSocial;
	}

	public StemDAOProducao getStemDAOProducao() {
		return stemDAOProducao;
	}

	public void setStemDAOProducao(StemDAOProducao stemDAOProducao) {
		this.stemDAOProducao = stemDAOProducao;
	}	
}
