class Word:

	def __init__(self,stopWordBannedList,stopWordConnectList):
		self.stopWordBannedList =  stopWordBannedList
		self.stopWordConnectList = stopWordConnectList

	def generate(self,palavras):
		wordList = []
		for palavra in palavras:
			if len(palavra[0:palavra.index("_")]) > 3 and palavra[0:palavra.index("_")] not in self.stopWordBannedList and palavra[0:palavra.index("_")] not in self.stopWordConnectList:
				wordList.append(palavra[0:palavra.index("_")])
		return wordList