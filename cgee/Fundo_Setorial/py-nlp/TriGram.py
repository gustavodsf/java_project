#! /usr/bin/env python
# -*- coding: utf-8 -*-

import time

class TriGram:

	def __init__(self,tagTrigram):
		self.tagTrigram = tagTrigram


	def generate(self,palavras):		
		trigramList = []
		for i in range(0,len(palavras)-2):
			for tag in self.tagTrigram:
				if  i < len(palavras) - 4  and len(tag) == 5  and tag[0] in palavras[i].strip() and tag[1] in palavras[i+1].strip() and tag[2] in palavras[i+2].strip() and tag[3] in palavras[i+3].strip() and tag[4] in palavras[i+4].strip():
					trigramList.append(palavras[i][0:palavras[i].index("_")] + " " + palavras[i+1][0:palavras[i+1].index("_")] + " " + palavras[i+2][0:palavras[i+2].index("_")]+ " " + palavras[i+3][0:palavras[i+3].index("_")]+ " " + palavras[i+4][0:palavras[i+4].index("_")])
				elif i < len(palavras) - 3 and len(tag) == 4  and tag[0] in palavras[i].strip() and tag[1] in palavras[i+1].strip() and tag[2] in palavras[i+2].strip() and tag[3] in palavras[i+3].strip():
					trigramList.append(palavras[i][0:palavras[i].index("_")] + " " + palavras[i+1][0:palavras[i+1].index("_")] + " " + palavras[i+2][0:palavras[i+2].index("_")]+ " " + palavras[i+3][0:palavras[i+3].index("_")])
				elif len(tag) == 3 and tag[0] in palavras[i].strip() and tag[1] in palavras[i+1].strip() and tag[2] in palavras[i+2].strip(): 
					trigramList.append(palavras[i][0:palavras[i].index("_")] + " " + palavras[i+1][0:palavras[i+1].index("_")] + " " + palavras[i+2][0:palavras[i+2].index("_")])
		return trigramList