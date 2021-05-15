#! /usr/bin/env python
# -*- coding: utf-8 -*-
import time
import nltk
import string
import re
from nltk import tokenize

class Util:

	def __init__(self):
		self.reader = Reader.Reader()
	
	def calcFreq(self,conteudo):
		print (":: Frequencia -> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
		return nltk.FreqDist(conteudo)

	def makeToken(self,conteudo):
		print (":: Token -> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
		return tokenize.wordpunct_tokenize(conteudo) 

	def cleanContent(self,cb,i):
		path = "/home/mis/file_" + str(i) + ".txt"
		conteudo = self.reader.readerFile(path)
		print (":: Retirando Pontuação -> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
		make = str.maketrans(string.punctuation,'                                ')
		conteudoLimpo = conteudo.translate(make)
		conteudoLimpo = conteudoLimpo.replace("?","")
		del conteudo  
		print (":: Retirando espaço duplicados -> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
		conteudo = conteudoLimpo.strip()
		conteudoLimpo = re.sub(' +',' ',conteudoLimpo)
		self.conteudo = conteudoLimpo
		print (":: Colocando LowerCase -> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
		return conteudoLimpo.lower()
	
	def cleanTriGram(self,words,freqDict):
		cleanedWord = []
		for i in range(0,len(words)):
			found = False
			key = ' '.join(words[i:i+3])
			key1 = ' '.join(words[i:i+4])
			key2 = ' '.join(words[i:i+5])
			if key in freqDict:
				found = True
				i +=3
			elif key1 in freqDict:
				found = True
				i+=4
			elif key2 in freqDict:
				found = True
				i+=5
			if not found:
				cleanedWord.append(words[i])
			else:
				if i == len(words)-3:
					return cleanedWord
		return cleanedWord



	def cleanBiGram(self,words,freqDict):
		cleanedWord = []
		for i in range(0,len(words)):
			found = False
			key = ' '.join(words[i:i+2])
			key1 = ' '.join(words[i:i+3])
			if key in freqDict:
				found = True
				i+=2
			elif key1 in freqDict:
				found = True
				i+=3
			if not found:
				cleanedWord.append(words[i])
			else:
				if i == len(words) -2:
					return cleanedWord
		return cleanedWord
			

	def cleanWindow(self,n,words,freqDict):
		cleanedWord = []
		for key in freqDict:
			n = key.count(" ");
			for i in range(0,len(words)):
				word = ' '.join(words[i:i+n])
				if key != word:
					cleanedWord.append(words[i])
		return cleanedWord