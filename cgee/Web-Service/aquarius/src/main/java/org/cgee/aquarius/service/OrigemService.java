package org.cgee.aquarius.service;

import java.util.List;

import org.cgee.aquarius.dao.OrigemDAO;
import org.cgee.aquarius.model.OrigemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("OrigemService")
@Transactional(readOnly = true)
public class OrigemService {
	
	@Autowired
	OrigemDAO origemDAO;

	public List<OrigemVO> getOrigem(String word1,String word2,String painel){
		return origemDAO.findOrigemByWords(word1, word2,painel);
	}
	
}
