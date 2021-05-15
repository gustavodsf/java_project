package org.cgee.aquarius.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cgee.aquarius.model.CloudVO;
import org.cgee.aquarius.model.StemProducao;
import org.cgee.aquarius.model.TermProducao;
import org.cgee.aquarius.service.StemService;
import org.cgee.aquarius.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/cloud")
public class CloudWS {
	
	@Autowired
	TermService termService;
	
	@Autowired
	StemService stemService;
	
	@GET
	@Path("/normal/{qtd}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<CloudVO> getTopTerm(@PathParam("qtd") int qtd,@PathParam("painel") String painel) {
		qtd  = qtd > 200 ? 200: qtd;
		return termService.getTop200NTerms(painel).subList(0, qtd);
	}
	
	@GET
	@Path("/ngram/{n}/{qtd}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<CloudVO> getTopNgram(@PathParam("n") int n,@PathParam("qtd") int qtd,@PathParam("painel") String painel) {
		qtd  = qtd > 200 ? 200: qtd;
		switch (n) {
			case 1:
				return termService.getTop200NGramTerms("word",painel).subList(0, qtd);
			case 2:
				return termService.getTop200NGramTerms("bigram",painel).subList(0, qtd);
			case 3:
				return termService.getTop200NGramTerms("trigram",painel).subList(0, qtd);
		}
		return new ArrayList<CloudVO>();
		
		
	}
	
	@GET
	@Path("/stem/{qtd}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<CloudVO> getTopStem(@PathParam("qtd") int qtd,@PathParam("painel") String painel) {
		return stemService.getTop200Stem(painel).subList(0, qtd);
	}
	
	@GET
	@Path("/find/term/like/{word}/{qtd}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<CloudVO> getTermSimilarToWord(@PathParam("word") String word,@PathParam("qtd") int qtd,@PathParam("painel") String painel) {
		qtd  = qtd > 200 ? 200: qtd;
		return termService.findTermByLike(word,painel).subList(0, qtd);
	}
}
