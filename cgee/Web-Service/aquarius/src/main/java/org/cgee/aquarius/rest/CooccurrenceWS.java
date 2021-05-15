package org.cgee.aquarius.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cgee.aquarius.model.CloudVO;
import org.cgee.aquarius.model.CooccurrenceListForWS;
import org.cgee.aquarius.model.CooccurrenceVO;
import org.cgee.aquarius.model.StemProducao;
import org.cgee.aquarius.service.StemService;
import org.cgee.aquarius.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/cooccurrence")
public class CooccurrenceWS {

	@Autowired
	TermService termService;
	
	@Autowired
	StemService stemService;
	
	@GET
	@Path("/term/{word}/{qtd}/{tipo}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public CooccurrenceListForWS getCooccurrenceOfWord(@PathParam("word") String word,@PathParam("qtd") int qtd,@PathParam("tipo") String tipo,@PathParam("painel") String painel) {
		CooccurrenceListForWS cooListWs = new CooccurrenceListForWS();
		if("radical".equals(tipo)){
			List<CooccurrenceVO> coolistWord = termService.getTermsOfStem(word,painel);
			cooListWs.setChildren(coolistWord);
			cooListWs.setName(word);
			
		}else{
			List<CooccurrenceVO>coolistWord = termService.getAllCooccurrence(word,qtd,painel);
			if(qtd < coolistWord.size()){
				cooListWs.setChildren(coolistWord.subList(0, qtd));
			}
			else{
				cooListWs.setChildren(coolistWord);
			}
			cooListWs.setName(word);
			cooListWs.setAmount(coolistWord.size());
			
		}
		return cooListWs;
	}
	
	
	@GET
	@Path("/most/frequent/{qtd}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public CooccurrenceListForWS getMostFrequent(@PathParam("qtd") int qtd,@PathParam("painel") String painel) {
		CooccurrenceListForWS cooListWs = new CooccurrenceListForWS();
		CloudVO cloud = termService.getTop200NTerms(painel).get(0);
		List<CooccurrenceVO>coolistWord = termService.getAllCooccurrence(cloud.getName(),qtd,painel);
		cooListWs.setChildren(coolistWord);
		cooListWs.setName("estudo");
		return cooListWs;
	}
	
	public void addStemToCooList(String word,StemProducao stem,List<CooccurrenceVO> cooList){
		if(word != null){
			CooccurrenceVO coo = new CooccurrenceVO();
			coo.setName(word);
			coo.setOccurrence(0);
			coo.setParent(stem.getStem());
			cooList.add(coo);
		}
		
	}
	
	public TermService getTermService() {
		return termService;
	}

	public void setTermService(TermService termService) {
		this.termService = termService;
	}
	public StemService getStemService() {
		return stemService;
	}
	public void setStemService(StemService stemService) {
		this.stemService = stemService;
	}


}