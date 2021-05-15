import Store

class Dict:

	def __init__(self,mediaSocialDAO,semaStem,semaWord,writeLogFile):

		self.wordDict = dict()
		self.stemDict = dict()
		self.usedWordId = dict()
		self.mediaSocialDAO = mediaSocialDAO
		self.semaWord = semaWord
		self.semaStem = semaStem
		self.writeLogFile = writeLogFile


	def addWordDict(self,word,idSource,generateID,typeWord):
		idWord = None
		idWordHasTxt = 0
		with self.semaWord:
			generateID.incrementIdWordHasTxt()
			idWordHasTxt = generateID.getIdWordHasTxt()

		#import pdb; pdb.set_trace()
		if word in self.wordDict.keys():
			self.wordDict[word].qtd += 1
			idWord = self.wordDict[word].id
		else:
			with self.semaWord:
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
		self.mediaSocialDAO.savePalavraHasOrigem(idWordHasTxt,idSource,idWord)

	def addStemDict(self,stem,word,idSource,generateID):
		idStem = None
		if stem in self.stemDict:
			self.stemDict[stem].qtd += 1
			self.stemDict[stem].wordIds.add(self.wordDict[word].id)
		else:
			with self.semaStem:			
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
		self.mediaSocialDAO.saveWordDict(self.wordDict)
		self.writeLogFile.writeOnFileHashWord(self.wordDict)
		print ("..:: Salvando os radicais ::..")
		self.mediaSocialDAO.saveStemDict(self.stemDict)
		self.writeLogFile.writeOnFileHashStem(self.stemDict)
