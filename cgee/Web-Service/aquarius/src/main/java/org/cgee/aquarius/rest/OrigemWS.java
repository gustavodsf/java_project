package org.cgee.aquarius.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cgee.aquarius.model.OrigemProducao;
import org.cgee.aquarius.model.OrigemVO;
import org.cgee.aquarius.service.OrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/origem")
public class OrigemWS {
	
	
	@Autowired
	OrigemService origemService;
	
	@GET
	@Path("/get/{word1}/{word2}/{painel}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<OrigemVO> getOrigem(@PathParam("word1") String word1,@PathParam("word2") String  word2,@PathParam("painel") String  painel) {
		return origemService.getOrigem(word1, word2,painel);
	}

	public OrigemService getOrigemService() {
		return origemService;
	}

	public void setOrigemService(OrigemService origemService) {
		this.origemService = origemService;
	}	
}
