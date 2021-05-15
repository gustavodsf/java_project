import Store

class Dict:

	def __init__(self,fundoSetorialDAO,pool_sema,writeLogFile):
		self.wordDict = dict()
		self.stemDict = dict()
		self.usedWordId = dict()
		self.fundoSetorialDAO = fundoSetorialDAO
		self.pool_sema = pool_sema
		self.writeLogFile = writeLogFile


	def addWordDict(self,word,idSource,generateID,typeWord):
		idWord = None
		idWordHasTxt = 0
		with self.pool_sema:
			generateID.incrementIdWordHasTxt()
			idWordHasTxt = generateID.getIdWordHasTxt()

		#import pdb; pdb.set_trace()
		if word in self.wordDict.keys():
			self.wordDict[word].qtd += 1
			idWord = self.wordDict[word].id
		else:
			with self.pool_sema:
				generateID.incrementIdWord()
				idWord = generateID.getIdWord()
				while  idWord in self.usedWordId:
					generateID.incrementIdWord()
					idWord = generateID.getIdWord()
				self.usedWordId[idWord]=True
				s = Store.Store()
				s.qtd = 1
				s.id = idWord
				s.type = typeWord
				self.wordDict[word]=s
		self.fundoSetorialDAO.savePalavraHasOrigem(idWordHasTxt,idSource,idWord)

	def addStemDict(self,stem,word,idSource,generateID):
		idStem = None
		if stem in self.stemDict:
			self.stemDict[stem].qtd += 1
			self.stemDict[stem].wordIds.add(self.wordDict[word].id)
		else:
			with self.pool_sema:			
				generateID.incrementIdStem()
				idStem = generateID.getIdStem()
				s = Store.Store()
				s.qtd = 1
				s.id = idStem
				self.stemDict[stem]=s
				self.stemDict[stem].wordIds.add(self.wordDict[word].id)

	def saveStemAndWordDict(self,cycle):
		self.writeLogFile.writeOnFileCycle(cycle)
		print ("..:: Salvando as palavras ::..")
		self.fundoSetorialDAO.saveWordDict(self.wordDict)
		self.writeLogFile.writeOnFileHashWord(self.wordDict)
		print ("..:: Salvando os radicais ::..")
		self.fundoSetorialDAO.saveStemDict(self.stemDict)
		self.writeLogFile.writeOnFileHashStem(self.stemDict)
