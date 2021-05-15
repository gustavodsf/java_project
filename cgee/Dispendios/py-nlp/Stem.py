import nltk

class Stem:

	def __init__(self,stopWordBannedList,stopWordConnectList):
		self.stopWordBannedList =  stopWordBannedList
		self.stopWordConnectList = stopWordConnectList
		self.stemmer = nltk.stem.RSLPStemmer()

	def generate(self,palavras):
		stemDict = dict()
		for palavra in palavras:
			if len(palavra[0:palavra.index("_")]) > 3 and palavra[0:palavra.index("_")] not in self.stopWordBannedList and palavra[0:palavra.index("_")] not in self.stopWordConnectList:
				stemDict[palavra[0:palavra.index("_")]] = self.stemmer.stem(palavra[0:palavra.index("_")])
		return stemDict