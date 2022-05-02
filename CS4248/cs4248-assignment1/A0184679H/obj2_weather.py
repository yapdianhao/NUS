'''
    NUS CS4248 Assignment 1 - Objective 2 (Weather)

    Class Weather for handling Objective 2
'''

import re

class Weather:

    # Class Constants for responses.
    SINGAPORE_WEATHER = "Singapore: hot and humid."
    LONDON_WEATHER = "London: rainy and miserable."
    CAIRO_WEATHER = "Cairo: bone dry."
    DEFAULT = "Hmm. That’s nice."

    def __init__(self):
        self.regexes = []
        self.words = [
            'weather',
            'temperature',
            'season',
            'hot',
            'warm',
            'cold',
            'cool',
            'sun',
            'rain',
            'cloud',
            'dry',
            'humid',
            'fog',
            'mist',
            'wind',
            'thunder'
            'storm',
            'freez'
        ]
        self.actions = [
            'tell',
            'inform',
            'describe',
            'state',
            'notif',
            'explain',
            'explan',
            'let me know',
            'ask',
            'quer',
            'advis',
        ]
        self.questionWords = ['how', 'what', 'is it']
        self.generateQuestionRegex()
        self.generateQuestionInverseRegex()
        self.generateSentenceRegex()
        self.generateSentenceInverseRegex()

    # how is the weather in singapore?
    def generateQuestionRegex(self):
        # to accept both upper and lower case, i was using (?i)word(?-i) but python does not support that
        # capture group 1 to determine the location
        regex = "^[\w\s,']*{}[\w\s,'.]*{}[\w\s,']+(Singapore|Cairo|London)[\w\s,']*[\.\?]??$"
        for questionWord in self.questionWords:
            for word in self.words:
                self.regexes.append(regex.format(questionWord, word))

    # is singapore sunny?
    def generateQuestionInverseRegex(self):
        regex = "^[\w\s,'.]*(Singapore|Cairo|London)['s|’s]*[\w\s,']*{}[\w\s,'.]*\?$"
        for word in self.words:
            self.regexes.append(regex.format(word))

    # let me know the weather in singapore.
    def generateSentenceRegex(self):
        regex = "^[\w\s,']*{}[\w\s,'.]*{}[\w\s,']*(Singapore|Cairo|London)[\w\s,']*[\.\?]??$"
        for action in self.actions:
            for word in self.words:
                self.regexes.append(regex.format(action, word))

    # please tell me Singapore's weather.
    def generateSentenceInverseRegex(self):
        regex = "^[\w\s,'.]*{}[\w\s,']*(Singapore|Cairo|London)['s|’s]*[\w\s]*{}[\w\s,']*[\.]??$"
        for action in self.actions:
            for word in self.words:
                self.regexes.append(regex.format(action, word))

    def weather(self,text):
        '''
        Decide whether the input is about the weather and
        respond appropriately
        '''
        for regex in self.regexes:
            matchGroups = re.match(regex, text, re.IGNORECASE)
            if matchGroups:
                capturedGroup = matchGroups.group(1).lower()
                if capturedGroup == 'singapore':
                    return self.SINGAPORE_WEATHER
                elif capturedGroup == 'london':
                    return self.LONDON_WEATHER
                elif capturedGroup == 'cairo':
                    return self.CAIRO_WEATHER
        return self.DEFAULT
                
        
