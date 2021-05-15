#! /usr/bin/env python
# -*- coding: utf-8 -*-
'''
Empresa: Funcao Coppetec
Desenvolvedor: Gustavo Daniel Soares Figueiredo
Data: 13/12/2014
Descricao: Classe criada para realizar a conexão entre o banco InfiniDB e o sistema.
'''

import MySqlDB

class MidiaSocialDAO:

	def __init__(self,writeLogFile):
		self.mysql = MySqlDB.MySqlDB()
		self.mysql.connect()
		self.writeLogFile = writeLogFile
		self.fileSource = open("error/log_erro_midia_social_source.txt", "w")
		self.fileWord   = open("error/log_erro_midia_social_word.txt"  , "w")
		self.fileStem   = open("error/log_erro_midia_social_stem.txt"  , "w")
		
	def getTableRowCount(self):
		cur = self.mysql.executeQuery("select count(*) from pg_midia_social")
		rowCount = cur.fetchone()[0]
		cur.close()
		return rowCount

	# retona limit números de linhas, de acordo com um ponto inicial dado pelo offset
	def getmidiaSocialOffsetLimit(self,limit,offset):
		cur = self.mysql.executeQuery("select * from pg_midia_social limit "+str(limit)+" offset "+str(offset))
		rows = cur.fetchall()
		cur.close();
		return rows
	
	def saveSource(self,row,idSource):
		try:
			txt = row[1]
			txt = txt.replace("\\","")
			txt = txt.replace("'","")
			query = "insert into midia_social_origem (idorigem,titulo,id_original) values ("+str(idSource)+",'"+txt+"',"+str(row[0])+")"
			cur = self.mysql.executeQuery(query)
			cur.close()
		except:
			self.fileSource.write(row[1]+"\n")
			

	def savePalavraHasOrigem(self,idWordSource,idSource,idWord):
		cur = self.mysql.executeQuery("insert into midia_social_origem_has_palavra (idorigem_has_palavra,idpalavra,idorigem) values ("+str(idWordSource)+","+str(idWord)+","+str(idSource)+")")
		cur.close()

	def saveWordDict(self,wordDict):
		cur = self.mysql.executeQuery("truncate midia_social_palavras")
		try:
			for key in wordDict:
				aux = key.replace("\\","")
				aux = aux.replace("'","")
				cur = self.mysql.executeQuery("insert into midia_social_palavras (idpalavras,palavra,qtd_word,tipo) values ("+str(wordDict[key].id)+",\'"+aux+"\',"+str(wordDict[key].qtd)+",\'"+wordDict[key].type+"\')")
			self.mysql.commit();
		except:
			self.fileWord.write(key+" | "+str(wordDict[key].id)+"\n")

	def saveStemDict(self,stemDict):
		try:
			cur = self.mysql.executeQuery("truncate midia_social_radical")
			for key in stemDict:
				aux = key.replace("\\","")
				aux = aux.replace("'","")
				cur = self.mysql.executeQuery("insert into midia_social_radical(idstem,stem,qtd_stem) values ("+str(stemDict[key].id)+",'"+aux+"',"+str(stemDict[key].qtd)+")")
				for wordId in stemDict[key].wordIds:
					cur = self.mysql.executeQuery("update midia_social_palavras set idstem="+str(stemDict[key].id)+" where idpalavras='"+str(wordId)+"' and tipo='word'")
			self.mysql.commit();
			cur.close()
		except:
			self.fileStem.write(key+" | "+str(stemDict[key].id)+"\n")