'''
    NUS CS4248 Assignment 1 - Objectives 1-3 Driver Code

    Classes Message, Echo, and helper functions for Slackbot
'''
from slack_sdk.rtm import RTMClient
import certifi
import ssl as ssl_lib
import re
from datetime import datetime
import json
import sys
import os
import obj1_tokenizer
import obj2_weather
import obj3_ngram_lm
# TODO add your imports here, so that you can have this main file use the
# classes defined for Objectives 1-3.

class Echo:

    def echo(self,text: str):
        ''' Echoes the text sent in '''
        reply = "You said: {}".format(text)
        return reply

class Message:

    def __init__(self, ts, username, text):
        self.ts = ts
        self.username = username
        self.text = text

    def toString(self):
        return f"Timestamp: {self.ts}, Username: {self.username}, Text: {self.text}"

    def toDict(self):
        data = {}
        data['Timestamp'] = self.ts
        data['Username'] = self.username
        data['Text'] = self.text
        return data

# List to keep track of messages sent and received
conversation = []

# TODO Make these whatever you want.
USERNAME = "CS4248 Bot of Awesomeness"
USER_EMOJI = ":dog:"

# TODO Copy your Bot User OAuth-Access Token and paste it here
SLACK_TOKEN = "xoxb-1663278582310-1670506522050-WxhasU9JWT8YcGshKP9jgNvH"

# You'll need to modify the code to call the functions that
# you've created in the rest of the exercises.
def make_message(user_input):
    ''' Driver function - Parses the user_input, calls the appropriate classes and functions
    and returns the output to the make_message() function

    Example input: user_input = "OBJ0 echo_text=Hi there"
    '''  

    # To stop the bot, simply enter the 'EXIT' command
    if user_input == 'EXIT':
        rtm_client.stop()
        with open('./conversation.json', 'w') as f:
            json.dump(conversation, f)
        return

    reply = ''
  
    # Regex matching and calling appropriate classes and functions
    pattern_dict = {
        "OBJ0":r"OBJ0 echo_text=(?P<echo_text>.*)",
        "OBJ1":r"OBJ1 path=(?P<path>.*) n_top_words=(?P<n_top_words>\d+) lowercase=(?P<lowercase>YES|NO) stopwords=(?P<stopwords>YES|NO)",
        "OBJ2":r"OBJ2 (?P<input_text>.*)",
        "OBJ3":r"OBJ3 path=(?P<path>.*) smooth=(?P<smooth>.*) n_gram=(?P<n_gram>\d) k=(?P<k>\d+(?:\.\d+)?) text=(?P<text>.*) next_word=(?P<next_word>.*) length=(?P<n_top_words>\d+)",
    }

    for key in pattern_dict.keys():
        match = re.match(pattern_dict[key], user_input)
        if match:
            # Dictionary with key as argument name and value as argument value
            commands_dict = match.groupdict()
            if key == "OBJ0":
                try:
                    print("[SUCCESS] Matched objective 0")
                    echo = Echo()
                    reply = echo.echo(commands_dict['echo_text'])
                except Exception as e:
                    print(e)
                break

            elif key == "OBJ1":
                print("[SUCCESS] Matched objective 1")
                # TODO complete objective 1 
                print(commands_dict)
                path = commands_dict['path']
                n_top_words = int(commands_dict['n_top_words'])
                lowercase = commands_dict['lowercase']
                stopwords = commands_dict['stopwords']
                tknz = obj1_tokenizer.Tokenizer(path, lowercase, stopwords)
                nTopWordsResult = tknz.get_frequent_words(n_top_words)
                for i in range(len(nTopWordsResult)):
                    word, freq = nTopWordsResult[i]
                    reply += "{}. \"{}\" with occurence: {}\n".format(i + 1, word, freq)
                break

            elif key == "OBJ2":
                print("[SUCCESS] Matched objective 2")
                text = commands_dict['input_text']
                weatherBot = obj2_weather.Weather()
                reply = weatherBot.weather(text)
                break
            
            elif key == "OBJ3":
                try:
                    print("[SUCCESS] Matched objective 3")
                    path = commands_dict['path']
                    smooth = commands_dict['smooth']
                    n_gram = int(commands_dict['n_gram'])
                    k = int(commands_dict['k'])
                    text = commands_dict['text']

                    next_word = commands_dict['next_word']
                    length = int(commands_dict['n_top_words'])
                    languageModel = obj3_ngram_lm.NgramLM(n_gram, k, smooth)
                    languageModel.read_file(path)
                    nextWord, nextWordProbability = languageModel.generate_word(text)
                    print("nextWord" ,text + ' ' + nextWord)
                    calculatedPerplexity = languageModel.perplexity(text + ' ' + nextWord)
                    print("calculated perplexity", calculatedPerplexity)
                    toFindProbability = languageModel.generate_probability(text, (next_word,))
                    print("to find prob", toFindProbability)
                    generatedText = languageModel.generate_text(length)
                    print(generatedText)
                    reply += 'Generated word: {}\n'.format(nextWord)
                    reply += 'Probability of next word: {}\n'.format(format(toFindProbability, '.10f'))
                    reply += 'Perplexity: {}\n'.format(calculatedPerplexity)
                    reply += 'Generated text: '
                    reply += generatedText
                    print(reply)
                    #print(languageModel.perplexity(generatedText))
                except Exception as e:
                    exc_type, exc_obj, exc_tb = sys.exc_info()
                    fname = os.path.split(exc_tb.tb_frame.f_code.co_filename)[1]
                    print(e, fname, exc_tb.tb_lineno)
                break
        
            else:
                print("[ERROR] Did not match any commands!")

    return reply


def do_respond(web_client, channel, text):
    # Post the message in Slack
    web_client.chat_postMessage(channel=channel,
                                username=USERNAME,
                                icon_emoji=USER_EMOJI,
                                text=make_message(text))

# ============== Message Events ============= #
# When a user sends a DM, the event type will be 'message'.
# Here we'll link the update_share callback to the 'message' event.
@RTMClient.run_on(event="message")

def message(**payload):
    """
    Call do_respond() with the appropriate information for all incoming
    direct messages to our bot.
    """
    web_client = payload["web_client"]

    # Getting information from the response
    data = payload["data"]
    channel_id = data.get("channel")
    text = data.get("text")
    subtype = data.get("subtype")
    ts = data['ts']
    user = data.get('username') if not data.get('user') else data.get('user')

    # Creating a Converstion object
    message = Message(ts, user, text)

    # Appending the converstion attributes to the logs
    conversation.append(message.toDict())

    if subtype == 'bot_message': return

    do_respond(web_client, channel_id, text)

# You probably won't need to modify any of the code below.
# It is used to appropriately install the bot.
def main():
    ssl_context = ssl_lib.create_default_context(cafile=certifi.where())
    # Real-time messaging client with Slack
    global rtm_client
    rtm_client = RTMClient(token=SLACK_TOKEN, ssl=ssl_context)
    try:
        print("[SUCCESS] Your bot is running!")
        rtm_client.start()
    except:
        print("[ERROR] Your bot is not running.")

if __name__ == "__main__":
    main()
