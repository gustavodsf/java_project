#! /usr/bin/env python
# -*- coding: utf-8 -*-
'''
Empresa: Funcao Coppetec
Desenvolvedor: Gustavo Daniel Soares Figueiredo
Data: 13/12/2014
Descricao: Classe criada para realizar a conexão entre o banco InfiniDB e o sistema.
'''

import MySqlDB

class ProducaoDAO:

	def __init__(self,writeLogFile):
		self.mysql = MySqlDB.MySqlDB()
		self.mysql.connect()
		self.writeLogFile = writeLogFile
		self.fileSource = open("error/log_erro_producao_source.txt", "w")
		self.fileWord   = open("error/log_erro_producao_word.txt"  , "w")
		self.fileStem   = open("error/log_erro_producao_stem.txt"  , "w")
		
	def getTableRowCount(self):
		cur = self.mysql.executeQuery("select count(*) from pg_producao")
		rowCount = cur.fetchone()[0]
		cur.close()
		return rowCount

	# retona limit números de linhas, de acordo com um ponto inicial dado pelo offset
	def getProducaoOffsetLimit(self,limit,offset):
		cur = self.mysql.executeQuery("select * from pg_producao limit "+str(limit)+" offset "+str(offset))
		rows = cur.fetchall()
		cur.close();
		return rows

	def getAreasDoConhecimento(self):
		cur = self.mysql.executeQuery("select * from area_de_conhecimento")
		rows = cur.fetchall()
		cur.close()
		return rows

	
	def saveSource(self,row,idSource,idAreaConhecimento):
		try:
			query = "insert into producao_origem (idorigem,titulo,id_original,id_area_conhecimento) values ("+str(idSource)+",'"+row[1]+"',"+str(row[0])+","+str(idAreaConhecimento)+")"
			cur = self.mysql.executeQuery(query)
			cur.close()
		except:
			self.fileSource.write(row[1]+"\n")
			

	def savePalavraHasOrigem(self,idWordSource,idSource,idWord):
		cur = self.mysql.executeQuery("insert into producao_origem_has_palavra (idorigem_has_palavra,idpalavra,idorigem) values ("+str(idWordSource)+","+str(idWord)+","+str(idSource)+")")
		cur.close()

	def saveWordDict(self,wordDict):
		cur = self.mysql.executeQuery("truncate producao_palavras")
		try:
			for key in wordDict:
				cur = self.mysql.executeQuery("insert into producao_palavras (idpalavras,palavra,qtd_word,tipo) values ("+str(wordDict[key].id)+",\'"+key+"\',"+str(wordDict[key].qtd)+",\'"+wordDict[key].type+"\')")
			self.mysql.commit();
		except:
			self.fileWord.write(key+" | "+str(wordDict[key].id)+"\n")

	def saveStemDict(self,stemDict):
		try:
			cur = self.mysql.executeQuery("truncate producao_radical")
			for key in stemDict:
				cur = self.mysql.executeQuery("insert into producao_radical(idstem,stem,qtd_stem) values ("+str(stemDict[key].id)+",'"+key+"',"+str(stemDict[key].qtd)+")")
				for wordId in stemDict[key].wordIds:
					cur = self.mysql.executeQuery("update producao_palavras set idstem="+str(stemDict[key].id)+" where idpalavras='"+str(wordId)+"' and tipo='word'")
			self.mysql.commit();
			cur.close()
		except:
			self.fileStem.write(key+" | "+str(stemDict[key].id)+"\n")