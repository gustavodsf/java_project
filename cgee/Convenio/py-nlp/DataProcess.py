#! /usr/bin/env python
# -*- coding: utf-8 -*-
'''
Empresa: Funcao Coppetec
Desenvolvedor: Gustavo Daniel Soares Figueiredo
Data: 13/12/2014
Descricao: Classe criada para processar cada linha da tabela em relação ao a geração de trigram, bigram, stem.
'''
import threading
import BiGram
import TriGram
import Word
import Stem
import GenerateID
import time

class DataProcess(threading.Thread):

	def __init__(self,stopWordBannedList,stopWordConnectList,tagTrigram,tagBigram,producaoDAO,row,semaSource,semaStem,semaWord,generateID,dicionario):
		threading.Thread.__init__(self)
		self.bigram = BiGram.BiGram(tagBigram)
		self.trigram = TriGram.TriGram(tagTrigram)
		self.word = Word.Word(stopWordBannedList,stopWordConnectList)
		self.stem = Stem.Stem(stopWordBannedList,stopWordConnectList)
		self.generateID = generateID
		self.producaoDAO = producaoDAO
		self.row = row
		self.semaSource = semaSource
		self.semaStem = semaStem
		self.semaWord = semaWord
		self.dicionario=dicionario

	def run(self):
		txt = self.row[2]
		if txt != None and isinstance(txt,(str)): 
			txt = txt.split(" ")
			trigramList = self.trigram.generate(txt)
			bigramList = self.bigram.generate(txt)
			wordList = self.word.generate(txt)
			stemDict = self.stem.generate(txt)
			
			idSource = 0
			with self.semaSource:
				self.generateID.incrementIdSource()
				idSource = self.generateID.getIdSource()

			self.addWordList(trigramList,idSource,"triGram")
			self.addWordList(bigramList,idSource,"bigram")
			self.addWordList(wordList,idSource,"word")
			self.addStemList(stemDict,idSource)
			self.producaoDAO.saveSource(self.row,idSource)


	def addWordList(self,wordList,idSource,typeWord):
		for word in wordList:
			self.dicionario.addWordDict(word,idSource,self.generateID,typeWord)

	def addStemList(self,stemDict,idSource):
		for key in stemDict:
			self.dicionario.addStemDict(stemDict[key],key,idSource,self.generateID)