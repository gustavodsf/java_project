#! /usr/bin/env python
# -*- coding: utf-8 -*-

import ReadInputFiles
import BolsaDAO
import math
import GenerateID
import DataProcess
import threading
import Dict
import WriteLogFile


class nlp:

	def __init__(self):
		# Instância  a classe que irá ler os arquivos de entrada do programa como stop-word e tags
		inputFiles = ReadInputFiles.ReadInputFiles()

		# Instância da classe generateID  que gera os id para os registros a serem adicionados no banco
		generateID = GenerateID.GenerateID()

		# Chama o método que cria todas os dicionários com os valores.
		inputFiles.mountDicts()

		writeLogFile = WriteLogFile.WriteLogFile()

		#Cria uma instância da classe Bolsa DAO, a qual fica realizando as consultas no banco
		bolsa = BolsaDAO.BolsaDAO(writeLogFile)

		pool_sema = threading.BoundedSemaphore(value=1)
		
		self.generateID = GenerateID.GenerateID()

		dicionario = Dict.Dict(bolsa,pool_sema,writeLogFile)

		numberOfThread = 4;

		#Pega a quantidade de linhas do banco de dados
		rowCount =  bolsa.getTableRowCount()

		areaDeConhecimento = self.montaHashAreaCohecimento(bolsa)

		# A quantidade de registros que serão trazidos do banco de dados por vez.
		offset = 5000
		# realiza a quantidade de vez para que se rode o algoritmo no banco todo.
		cycles = math.ceil(rowCount/offset)

		i= inputFiles.readOnFileCycle()
		
		inputFiles.readOnFileHashWord(dicionario.wordDict)
		inputFiles.readOnFileHashStem(dicionario.stemDict)

		for i in range(0,cycles):
			#pegando as 1000 registros do banco de cada vez
			rows = bolsa.getBolsaOffsetLimit(offset,offset*i)
			threads = []
			k=0
			while k < len(rows):
				for l in range(0,numberOfThread):
					if k < len(rows):
						thread = DataProcess.DataProcess(inputFiles.stopWordBannedList,inputFiles.stopWordConnectList,inputFiles.tagTrigram,inputFiles.tagBigram,bolsa,rows[k],pool_sema,self.generateID,areaDeConhecimento,dicionario)
						threads.append(thread)
						thread.start()
						k=k+1
				for l in range(0,len(threads)):
					threads[l].join()
				del threads[:]
			print ("..::Já foram processadas ->"+ str(offset*(i+1)))
			bolsa.mysql.commit();
			if i > 0 and i%10==0:
				dicionario.saveStemAndWordDict(i)
		#fechando os arquivos de log que contem os erros de insercao de cada tabela
		bolsa.fileSource.close()
		bolsa.fileWord.close()
		bolsa.fileStem.close()
		dicionario.saveStemAndWordDict(i)
		
	def montaHashAreaCohecimento(self,bolsa):
		rows = bolsa.getAreasDoConhecimento();
		dicAreaConhecimento = dict()
		for row in rows:
			key = row[1]+row[2]
			dicAreaConhecimento[key]=row[0]
		return dicAreaConhecimento