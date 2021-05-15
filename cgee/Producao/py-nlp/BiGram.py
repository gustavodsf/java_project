#! /usr/bin/env python
# -*- coding: utf-8 -*-
class BiGram:

	def __init__(self, tagBigram):
		self.tagBigram = tagBigram

	def generate(self,palavras):		
		bigramList = []
		for i in range(0,len(palavras)-1):
			for tag in self.tagBigram:

				if  i < len(palavras) - 2  and len(tag) == 3  and tag[0] in palavras[i].strip() and tag[1] in palavras[i+1].strip() and tag[2] in palavras[i+2].strip():
					bigramList.append(palavras[i][0:palavras[i].index("_")] + " " + palavras[i+1][0:palavras[i+1].index("_")] + " " + palavras[i+2][0:palavras[i+2].index("_")])
				elif i < len(palavras) - 1 and len(tag) == 2  and tag[0] in palavras[i].strip() and tag[1] in palavras[i+1].strip():
					bigramList.append(palavras[i][0:palavras[i].index("_")] + " " + palavras[i+1][0:palavras[i+1].index("_")])
		return bigramList


