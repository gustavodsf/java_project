class WriteLogFile:
	def writeOnFileCycle(self,cycle):
			self.fileCycle      = open("log/log_cycle.txt", "w")
			self.fileCycle.write(str(cycle))
			self.fileCycle.close()

	def writeOnFileHashWord(self,wordDict):
			self.fileHashWord   = open("log/log_hashmap_word.txt"  , "w")
			for key in wordDict:
				line = key+";"+str(wordDict[key].id)+";"+str(wordDict[key].qtd)+";"+wordDict[key].type+"\n"
				self.fileHashWord.write(line)
			self.fileHashWord.close()

	def writeOnFileHashStem(self,stemDict):
			self.fileHashStem   = open("log/log_hashmap_stem.txt"  , "w")
			for key in stemDict:
				line = key+";"+str(stemDict[key].id)+";"+str(stemDict[key].qtd)+";"
				for wordId in stemDict[key].wordIds:
					line = line + str(wordId) + ","
				line = line[0:line.index(",")]
				self.fileHashStem.write(line+"\n")
			self.fileHashStem.close()