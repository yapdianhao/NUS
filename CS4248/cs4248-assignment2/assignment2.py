#!/usr/bin/env python.

"""
CS4248 ASSIGNMENT 2 Template

TODO: Modify the variables below.  Add sufficient documentation to cross
reference your code with your writeup.

"""

# Import libraries.  Add any additional ones here.
# Generally, system libraries precede others.
import nltk
import re
import numpy as np
import pandas as pd
from nltk.corpus import stopwords
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline
from sklearn.metrics import f1_score

# TODO: Replace with your Student Number
_STUDENT_NUM = 'A0184679H'

stopwords_list = stopwords.words('english')

def preprocess(text):
    text = re.sub(r'[^\w\s]', '', text.lower().strip())
    splitted_text = text.split(' ')
    removed_stopwords_text = [word for word in splitted_text if word not in stopwords_list]
    stemmer = nltk.stem.porter.PorterStemmer()
    stemmed_text = [stemmer.stem(word) for word in removed_stopwords_text]
    return ' '.join(stemmed_text)

def train_model(model, X_train, Y_train):
    model.fit(X_train, Y_train)
    
def predict(model, X_test):
    ''' TODO: make your prediction here '''
    prediction = model.predict(X_test)
    return prediction

def generate_result(test, y_pred, filename):
    ''' generate csv file base on the y_pred '''
    test['Verdict'] = pd.Series(y_pred)
    test.drop(columns=['Text'], inplace=True)
    test.to_csv(filename, index=False)
    
def main():
    ''' load train, val, and test data '''
    train = pd.read_csv('train.csv')
    # uncomment below to apply preprocessing of training data
    #train['Text'] = train['Text'].apply(preprocess)
    X_train = train['Text']
    y_train = train['Verdict']
    
    model = Pipeline([
        ('vect', CountVectorizer(ngram_range = (1, 2), lowercase = False, max_df = 0.8)), 
        ('tfidf', TfidfTransformer(use_idf = False, sublinear_tf = True)), 
        ('clf', LogisticRegression(solver = 'sag', penalty = 'l2', C = 1000, max_iter = 50000))
    ])

    # code below for empirical model testing for analysis
    # model = Pipeline([
    #     ('vect', CountVectorizer()), 
    #     ('tfidf', TfidfTransformer()), 
    #     ('clf', LogisticRegression())
    # ])

    train_model(model, X_train, y_train)
    # test your model
    y_pred = predict(model, X_train)

    # Use f1-macro as the metric
    score = f1_score(y_train, y_pred, average='macro')
    print('score on validation = {}'.format(score))
    
    # generate prediction on test data
    test = pd.read_csv('test.csv')
    # uncomment to apply preprocessing of testing data
    #test['Text'] = test['Text'].apply(preprocess)
    X_test = test['Text']
    y_pred = predict(model, X_test)
    generate_result(test, y_pred, _STUDENT_NUM + ".csv")

# Allow the main class to be invoked if run as a file.
if __name__ == "__main__":
    main()
