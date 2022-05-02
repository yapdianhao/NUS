'''
    NUS CS4248 Assignment 1 - Objective 3 (n-gram Language Model)

    Class NgramLM for handling Objective 3
'''
import random, math
import string
import collections
import re, sys, os

class NgramLM(object):

    def __init__(self, n, k, smoothingMethod):
        '''
            Initialize your n-gram LM class

            Parameters:
                n (int) : order of the n-gram model
                k (float) : smoothing hyperparameter

        '''
        # Initialise other variables as necessary
        # TODO Write your code here
        self.n = n
        self.k = k
        self.nGramsFreqDict = collections.defaultdict(int)
        self.smoothingMethod = smoothingMethod
        self.context = collections.defaultdict(list)
        self.vocabulary = dict()

    def update_corpus(self, corpus):
        ''' Updates the n-grams corpus based on text '''
        tokenizedCorpus = self.tokenize(corpus) # treat each punctuation as a word
        self.vocabulary = self.get_vocabulary(tokenizedCorpus)
        paddedCorpus = self.add_padding(tokenizedCorpus)
        nGrams = self.ngrams(paddedCorpus.split())
        for nGram in nGrams:
            prevWords, currWord = nGram
            nWords = prevWords + (currWord,)
            self.nGramsFreqDict[nWords] += 1
            self.context[prevWords].append(currWord)

    def read_file(self, path):
        ''' Read the file and update the corpus  '''
        with open(path, encoding='utf-8', errors='ignore') as f:
            corpus = f.read()
            self.update_corpus(corpus)

    def tokenize(self, corpus):
        # treats punctuation as a word.
        # changes next line to a space
        for symbol in string.punctuation:
            corpus = corpus.replace(symbol, ' ' + symbol + ' ')
        corpus = corpus.replace("\n", " ")
        return corpus

    def ngrams(self, wordList):
        ''' Returns ngrams of the text as list of pairs - [(sequence context, word)] '''
        # generate all 1, 2, 3, ...n - 1 grams
        nGrams = []
        for i in range(len(wordList) - self.n):
            prevWords = tuple()
            currWord = wordList[i + self.n - 1]
            for j in range(i, i + self.n):
                if len(prevWords) >= 1:
                    self.context[prevWords].append(wordList[j])
                prevWords += (wordList[j],)
                self.nGramsFreqDict[prevWords] += 1
            nGrams.append((prevWords, currWord))
        return nGrams

    def add_padding(self, text):
        '''  Returns padded text '''
        # TODO Write your code here
        # Use '~' as your padding symbol
        # pads n - 1 ~ before each sentence
        padding = ''
        for i in range(self.n - 1):
            padding += '~ '
        text = text.split(".")
        for i in range(len(text)):
            text[i] = "~ ~ " + text[i]
        return " . ".join(text)

    def get_vocabulary(self, corpus):
        ''' Returns the vocabulary as set of words '''
        # returns the set of unique words in the corpus
        regex = r'\b\w+\b'
        words = re.findall(regex, corpus)
        return words

    def get_next_word_probability(self, text, word):
        ''' Returns the probability of word appearing after specified text '''
        if text == ('UNK',):
            return self.smooth(self.nGramsFreqDict[(word,)], self.nGramsFreqDict[text])
        numerator = self.nGramsFreqDict[text + (word,)]
        denominator = self.nGramsFreqDict[text]
        if (numerator == 0 or denominator == 0) and self.k == 0:
            return 0
        return self.smooth(numerator, denominator)

    def generate_probability(self, text, word):
        # calculates the probability of the next word based on the sentence
        for punct in string.punctuation:
            text = text.replace(punct, ' ' + punct + ' ')
        text = text.split()
        if len(text) > self.n - 1:
                text = tuple(text[self.n - 1:])
        numerator = self.nGramsFreqDict[text + (word,)]
        denominator = self.nGramsFreqDict[text]
        if (numerator == 0 or denominator == 0) and self.k == 0:
            return 0
        return self.smooth(numerator, denominator)


    def generate_word_based_on_context(self, currWords):
        '''
        Returns a random word based on the specified text and n-grams learned
        by the model
        '''
        # use backoff if n-gram not learned.
        probabilityMap = dict()
        if currWords not in self.context:
            return self.backoff(currWords[1:])
        for word in self.context[currWords]:
            probabilityMap[word] = self.get_next_word_probability(currWords, word)
        # nGram not in nGramFreqDict. What to do?
        randomProbability = random.uniform(0, sum(probabilityMap.values()))
        cumulativeProbability = 0
        for word, probability in sorted(probabilityMap.items(), key = lambda x: x[1]):
            cumulativeProbability += probability
            if cumulativeProbability >= randomProbability:
                return (word, probability)

    def generate_word(self, text):
        # generates the next word based on the input text
        for symbol in string.punctuation:
            text = text.replace(symbol, ' ' + symbol + ' ')
        text = text.split()
        if len(text) < self.n - 1:
            text = ['~'] * (self.n - 1 - len(text)) + text
        return self.generate_word_based_on_context(tuple(text[-self.n + 1:]))
        
    def generate_text(self, length):
        ''' Returns text of the specified length based on the learned model '''
        ans = []
        currWords = collections.deque()
        vocab = self.vocabulary
        for i in range(self.n - 1):
            currWords.append('~')
        while len(ans) < length:
            if self.smoothingMethod == 'add-k':
                nextWord, nextWordProbability = self.generate_word_based_on_context(tuple(currWords))
                currWords.append(nextWord)
                currWords.popleft()
                # does not append padding to the generated text
                if nextWord != '~':
                    ans.append(nextWord)
            elif self.smoothingMethod == 'backoff':
                nextWord, nextWordProbability = self.backoff(tuple(currWords))
                currWords.append(nextWord)
                currWords.popleft()
                if nextWord != '~':
                    ans.append(nextWord)
        return ' '.join(ans)

    def smooth(self, numerator, denominator):
        return (numerator + self.k) / (denominator + self.k * len(self.context))

    def handleUnknownWords(self, unknownWord):
        self.context[('UNK',)].append('<UNK>')
        self.nGramsFreqDict[unknownWord] += 1
        self.nGramsFreqDict['UNK'] += 1
        return self.generate_word_based_on_context(('UNK',))

    def backoff(self, prevWords):
        if len(prevWords) == 1 and prevWords not in self.context:
            return self.handleUnknownWords(prevWords)
        elif prevWords in self.context:
            return self.generate_word_based_on_context(prevWords)
        else:
            return self.generate_word_based_on_context(prevWords[1:])

    def perplexity(self, text):
        for punct in string.punctuation:
            text = text.replace(punct, ' ' + punct + ' ')
        text = '~ ' * (self.n - 1) + text
        text = text.split()
        ppx = 0
        for i in range(len(text) - self.n + 1):
            currWords = tuple(text[j] for j in range(i, i + self.n))
            currWord = currWords[-1]
            prevWords = currWords[:-1]
            currProbability = self.get_next_word_probability(prevWords, currWord)
            if currProbability > 0:
                ppx += math.log(currProbability)
        ppx *= -(1 / len(text))
        return math.e ** (ppx)
        

        
