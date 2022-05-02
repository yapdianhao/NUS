'''
    NUS CS4248 Assignment 1 - Objective 1 (Tokenization)

    Class Tokenizer for handling Objective 1
'''
## Suggested libraries -- uncomment the below if you want to use these
## recommended resources and libraries.

import nltk
nltk.download('stopwords')
from nltk.corpus import stopwords   # Requires NLTK in the include path.
import matplotlib.pyplot as plt     # Requires matplotlib to create plots.
import re
import sys
import os
import math
import collections

## List of stopwords
STOPWORDS = stopwords.words('english') # type: list(str)
class Tokenizer:

    def __init__(self, path, isLowerCase, useStopWords):
        self.isLowerCase = False if isLowerCase == 'NO' else True
        self.useStopWords = False if useStopWords == 'NO' else True
        with open(path, encoding='utf-8', errors='ignore') as f:
            self.text = f.read()
            self.splitEmptyLines = self.splitWithNextLineChar(self.text)
            self.freqDict = self.tokenize()

    def splitWithNextLineChar(self, corpus):
        return corpus.split("\n")

    def tokenize(self):
        ''' Returns a set of word tokens '''
        if self.isLowerCase:
            self.text = self.text.lower()
        self.text = re.sub('[^a-zA-Z]+', '*', self.text)
        freqDict = collections.Counter(self.text.split("*"))
        if not self.useStopWords:
            self.remove_stopwords(freqDict)
        # Uncomment the line below to plot the graph
        # self.plot_word_frequency(freqDict) 
        return freqDict

    def get_frequent_words(self, n):
        ''' Returns the most frequent unigrams from the text '''
        return self.freqDict.most_common(n)

    def plot_word_frequency(self, freqDict):
        '''
        Plot relative frequency versus rank of word to check
        Zipf's law
        Relative frequency f = Number of times the word occurs /
                                Total number of word tokens
        Rank r = Index of the word according to word occurence list
        '''
        # TODO Modify the code here
        k = list(map(lambda x: math.log(x) if x != 0 else 0, range(len(freqDict))))
        v = list(map(lambda x: math.log(x), freqDict.values()))
        v.sort(reverse = True)
        plt.plot(k, v)
        figure = plt.gca()
        plt.xlabel('Rank (log)')
        plt.ylabel('Frequency (log)')
        plt.show()

    def remove_stopwords(self, freqDict):
        ''' Removes stopwords from the text corpus '''
        # TODO Modify the code here
        for stopWord in STOPWORDS:
            if stopWord in freqDict:
                del freqDict[stopWord]
