#! /usr/bin/env python
# -*- coding: utf-8 -*-
'''
Empresa: Funcao Coppetec
Desenvolvedor: Gustavo Daniel Soares Figueiredo
Data: 13/12/2014
Descricao: Classe criada para realizar a conexão entre o banco InfiniDB e o sistema.
'''

# bibloteca do python que ajuda a  manipular  o banco de dados
import pymysql

class MySqlDB:
	conn = None

	# Metodo criado para realizar com a conexao com banco de dados Mysql
	def connect(self):
		self.conn = pymysql.connect(host='localhost', port=3306, user='root', passwd='123456', db='pgstag_coppe', autocommit=False)

	#Metodo utilizado para executar  um query e retorar seu resultado
	def executeQuery (self,query):
		cur = self.conn.cursor()
		cur.execute(query)
		return cur

	#Método criado para fechar a conexão com o banco de dados.
	def disconnect(self):
		self.conn.close()

	def commit(self):
		self.conn.commit()

	def rollback(self):
		self.conn.rollback()