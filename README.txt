__Class Structure__

GuiMain Class:
-main()/start(): This method intializes the Bot and sets all needed variables for it.
    This method also creates the gui and now handles all the input and displays the output on the gui.
-outputMsg(msg,color,container,scene): This method displays a message on the gui in a
    given colour
-initializeBot(bot): this just initializes the data for the dialogues

Bot Class:
-Bot(name): initializing method, sets name of bot
-createResponseList(list): Initializes and sets the keys and responses list
-getSpecial(match,input): this method returns any special cases of responses
    that require additional conditions and variables
-getWeather(city): this method builds a string for the weather information
    of a given city
-getName(): this method returns the name of the bot
-getResponse(input): this method does the pattern matching and input checking.
    If there are any special input matches it calls getSpecial().
-checkSynonym(word1,word2): this method checks if word1 is a synonym of word2

Weather Class: Gather weather information of a city
-weather(location): Initialize Weather Class and perform a GET request to open weather API
-getTemp(): return the temperature of the city
-getWeatherMain(): return the main description of the weather
-getWeatherDesc(): this method returns a more thorough description of the city's weather
-setWeatherInfo(): this method processes parse the given JSON and sets the weather information fields
-setTemp(): parse given JSON and gets the weather temperature

SpellCheck class : Perform a spelling check on a sentence
-SpellCheck(): Constructor for class and call setNewSentence method
-setNewSentence(sentence): set new sentence for the class and call process method
-process(): process the given sentence and set fixed sentence member field with the corrected sentence (if any)
-getCorrectedSentence(): returns the corrected sentence
-getRawSentence(): returns the unprocessed sentence, if needed


Features:
-Spelling Checker: This feature was added in attempt to correct any spelling mistakes
    that might occur when messaging the bot
    Example:
        Me: What is the weeather like?
        Sandra: We have broken clouds in Kelowna and the temperature is 10.82 °C
-GUI: Improved the interface and simplicity for interacting with the bot and
    also allowing a scrollable history with better readability
    Example: Unable to provide text example
-Synonym checker and POS tagger: This used the Rita and wordnet api/library to
    allow for similar words to be used and still find a text match increasing the
    number of ways a question could be asked
    Example: (Note: In this case 'unsafe' was matched with 'dangerous')
        Me: is climbing unsafe
        Sandra: Rock Climbing can be dangerous if done improperly, but generally, if done correctly, it is very safe.
-Weather checker: Uses an api to check the realtime weather and gives an accurate
    response that works with many different cities
    Example:
        Me: How is the weather in Winnipeg?
        Sandra: We have overcast clouds in winnipeg and the temperature is -0.85 °C
-Rock Climbing Topic: Another topic was added to discover hikes and rock climbing   
    locations and ask general questions about it.
    Example:
        Me: what are some good places to go hike?
        Sandra: Some good hikes might be Christie Falls, Bear Creek, Myra Canyon, and Fintry Falls
-Off topic responses: The bot has responses if a question is off topic.
    Example:
        Me: How was the universe created?
        Sandra: Sorry, while trying to figure out your question I ran out of readable memory space allocation oil.