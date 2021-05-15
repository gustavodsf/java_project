#! /usr/bin/env python
# -*- coding: utf-8 -*-
'''
Empresa: Funcao Coppetec
Desenvolvedor: Gustavo Daniel Soares Figueiredo
Data: 14/12/2014
Descricao: Classe criada para gerar os id dos objetos a serem adicionados no banco de dados, evitando assim mais requisições ao banco de dados.
'''

class GenerateID:

	def __init__(self):
		# Variável que armazena o último id das palavras
		self.idWord = 0
		# Variável que armazena o último id do mapeamento entre palavra de id
		self.idwordHasTxt = 0
		# Variável que armazena o último id dos títulos originais.
		self.idSource = 0
		# Variável que armazena o último id do radical a ser adicionado.
		self.idStem = 0

	def getIdWord(self):
		return self.idWord

	def getIdWordHasTxt(self):
		return self.idwordHasTxt

	def getIdSource(self):
		return self.idSource

	def getIdStem(self):
		return self.idStem;

	def incrementIdWord(self):
		self.idWord = self.idWord + 1

	def incrementIdWordHasTxt(self):
		self.idwordHasTxt = self.idwordHasTxt + 1

	def incrementIdSource(self):
		self.idSource = self.idSource + 1

	def incrementIdStem(self):
		self.idStem = self.idStem + 1