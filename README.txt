Class Structure

Main Class:
-Main(): This method intializes the Bot and sets all needed variables for it.
    It also asks the user for input and then passes it to the Bot to pattern match.

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
