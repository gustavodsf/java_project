#! /usr/bin/env python
# -*- coding: utf-8 -*-

'''
Empresa: Funcao Coppetec
Desenvolvedor: Gustavo Daniel Soares Figueiredo
Data: 13/12/2014
Descrição: classe que realiza a leitura das stop-words e das tags do OPEN-NLP
'''

# Biblioteca utilizada para pegar o diretório atual
import os
# Biblioteca utilizada para  imprimir a presente hora
import time
import Store

class ReadInputFiles:

    def __init__(self):
        # Dicionário dos caracteres banidos que devem ser removidos dos textos
        self.stopWordBannedList = dict()
        # Dicionário que contém as stop-words de ligação onde estas devem aparecer apenas no Bi-Gram e Tri-Gram
        self.stopWordConnectList = dict()
        # Dicionário utiliza para armazenar as tags do trigram, que mais a frente será utilizado para filtar o espaço de contagem
        self.tagTrigram = [];
         # Dicionário utiliza para armazenar as tags do bigram que mais a frente será utilizado para filtar o espaço de contagem
        self.tagBigram = [];
        #diretório atual
        self.path = os.path.dirname(os.path.realpath(__file__))

    # Método chamado para montar todos os dicionários
    def mountDicts(self):
       self.mountStopWordBanned()
       self.mountStopWordConnect()
       self.mountBigramTagList()
       self.mountTrigramTagList()

    def mountStopWordBanned(self):
        print (":: Stop Word para remover-> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
        arq = open(self.path+'/file/stop_word_remove.txt', "r") # Le dos arquivos as stop words para remover
        for line in arq: #le linha por linha do arquivo 
            self.stopWordBannedList[line.replace('\n', '')] = line.replace('\n', '')#adiciona ao dicionário

    def mountStopWordConnect(self):
        print (":: Stop Word para connectar-> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
        arq = open(self.path+'/file/stop_word_conect.txt', "r") # Le dos arquivos as stop words que são consideradas de conexão
        for line in arq: # le linha por linha do arquivo
            self.stopWordConnectList[line.replace('\n', '')] = line.replace('\n', '')

    def mountBigramTagList(self): 
        print (":: Monta Bi-Gram Tags-> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
        arq = open(self.path+'/file/bigram_tags.txt', "r")
        for line in arq:
            self.tagBigram.append(line.replace('\n', '').split(','))

    def mountTrigramTagList(self): 
        print (":: Monta Tri-Gram Tags-> %s" % time.strftime("%d/%m/%Y %H:%M:%S"))
        arq = open(self.path+'/file/trigram_tags.txt', "r")
        for line in arq:
            self.tagTrigram.append(line.replace('\n', '').split(','))

    def readOnFileCycle(self):
        arq = open("log/log_cycle.txt", "r")
        for line in arq:
            return int(line)
        return 0

    def readOnFileHashWord(self,wordDict):
        arq = open("log/log_hashmap_word.txt"  , "r")
        for line in arq:
            line = line.replace("\n","")
            line = line.split(";")
            s = Store.Store()
            s.qtd = int(line[2])
            s.id = int(line[1])
            s.type = line[3]
            wordDict[line[0]]=s
        arq.close()

    def readOnFileHashStem(self,stemDict):
        arq   = open("log/log_hashmap_stem.txt"  , "r")
        for line in arq:
            line = line.replace("\n","")
            line = line.split(";")
            #line = key+";"+str(stemDict[key].id)+";"+str(stemDict[key].qtd)+";"
            s = Store.Store()
            s.qtd = int(line[2])
            s.id = int(line[1])
            for wordId in line[3].split(","):
                s.wordIds.add(wordId)
            stemDict[line[0]]=s
        arq.close()