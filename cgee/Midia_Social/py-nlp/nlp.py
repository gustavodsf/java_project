#! /usr/bin/env python
# -*- coding: utf-8 -*-

import ReadInputFiles
import MidiaSocialDAO
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
		midiaSocial = MidiaSocialDAO.MidiaSocialDAO(writeLogFile)

		semaSource = threading.BoundedSemaphore(value=1)
		semaStem = threading.BoundedSemaphore(value=1)
		semaWord = threading.BoundedSemaphore(value=1)

		self.generateID = GenerateID.GenerateID()

		dicionario = Dict.Dict(midiaSocial,semaStem,semaWord,writeLogFile)

		numberOfThread = 4;

		#Pega a quantidade de linhas do banco de dados
		rowCount =  midiaSocial.getTableRowCount()

		# A quantidade de registros que serão trazidos do banco de dados por vez.
		offset = 5000
		# realiza a quantidade de vez para que se rode o algoritmo no banco todo.
		cycles = math.ceil(rowCount/offset)

		i= inputFiles.readOnFileCycle()
		
		inputFiles.readOnFileHashWord(dicionario.wordDict)
		inputFiles.readOnFileHashStem(dicionario.stemDict)

		for i in range(0,cycles):
			#pegando as 1000 registros do banco de cada vez
			rows = midiaSocial.getmidiaSocialOffsetLimit(offset,offset*i)
			threads = []
			k=0
			while k < len(rows):
				for l in range(0,numberOfThread):
					if k < len(rows):
						thread = DataProcess.DataProcess(inputFiles.stopWordBannedList,inputFiles.stopWordConnectList,inputFiles.tagTrigram,inputFiles.tagBigram,midiaSocial,rows[k],semaSource,semaStem,semaWord,self.generateID,dicionario)
						threads.append(thread)
						thread.start()
						k=k+1
				for l in range(0,len(threads)):
					threads[l].join()
				del threads[:]
			print ("..::Já foram processadas ->"+ str(offset*(i+1)))
			midiaSocial.mysql.commit();
			if i > 0 and i%10==0:
				dicionario.saveStemAndWordDict(i)
		#fechando os arquivos de log que contem os erros de insercao de cada tabela
		midiaSocial.fileSource.close()
		midiaSocial.fileWord.close()
		midiaSocial.fileStem.close()
		dicionario.saveStemAndWordDict(i)
		
