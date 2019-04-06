import rita.RiWordNet;

import java.util.Arrays;

public class Bot {
    String name;
    String[][] keyAns;
    int bestMatch;
    SpellCheck spell;
    RiWordNet wordnet = new RiWordNet("C:\\Program Files (x86)\\WordNet\\2.1\\");

    Bot(String name) {
        this.name = name;
        bestMatch = -1;
        //initialize spell checker
        try {
            spell = new SpellCheck("");
        } catch (Exception e) {
        }
    }

    public void createResponseList(String[][] list) {
        keyAns = list;
        for (int x = 0; x < keyAns.length; x++) {
            for (int y = 0; y < keyAns[x].length - 1; y++) {
                keyAns[x][y] = keyAns[x][y].replaceAll("[^A-Za-z0-9/ ]", "");
            }
        }
    }

    public String getResponse(String input) {
        //for getting location of previous match
        if (input.toLowerCase().equals("/get match")) {
            if (bestMatch != -1)
                return "" + bestMatch;
        }

        //try to check for spelling errors
        try {
            spell.setNewSentence(input);
        } catch (Exception e) {
        }
        String corrected = spell.getCorrectedSentence();
        corrected = corrected.toLowerCase();
        corrected = corrected.replaceAll("[^A-Za-z0-9 ]", "");

        //remove any non-alphabet/space characters
        input = input.replaceAll("[^A-Za-z0-9 ]", "");
        input = input.toLowerCase();

        //this for loop checks once without modified sentence and if no match is found
        // it check with 'corrected' sentence
        //Note: This is to prevent actual words from being 'corrected' (which were already correct)
        String[] in = null;
        bestMatch = -1;
        for (int temp = 0; bestMatch==-1 && temp<= 1; temp++) {
            //split input into separate words
            in = input.split(" ");
            //find best match for response
            int matchCount = 0;
            //first loop goes through list of sentences to match
            for (int x = 0; x < keyAns.length; x++) {
                int pos = 0;
                //goes through list of input words
                for (int y = 0; y < in.length; y++) {
                    //if remaining input words is less than positions to match, break out of loop
                    if (in.length - y < keyAns[x].length - pos - 1)
                        break;
                    //split all words with a backslash (/) and compare each split word individually
                    String[] keys = keyAns[x][pos].split("//");
                    for (int i = 0; i < keys.length; i++) {
                        //if there is a match or a matched synonym, the position increments, and once all
                        //words from keywords are matched, it is the best match
                        if (in[y].equals(keys[i].toLowerCase()) || checkSynonym(keys[i].toLowerCase(), in[y])) {
                            pos++;
                            //if all keywords are matched
                            if (pos >= keyAns[x].length - 1 && matchCount < pos) {
                                i = keys.length;
                                matchCount = pos;
                                bestMatch = x;
                                y = in.length;
                            }
                        }
                    }
                }
            } //end of loops
            //if bestmatch is still -1, try to search for input with a spelling mistakes corrected
            input = corrected;
        }
        //check if there is a special case for these keywords
        String sp = this.getSpecial(bestMatch, in);
        String botResp = "";

        if (sp.length() > 0) {
            botResp = sp;
        } else if (bestMatch != -1) {
            botResp = keyAns[bestMatch][keyAns[bestMatch].length - 1];
        } else if (in.length <= 1) {
            botResp = "Ok";
        } else {
            int rand = (int) Math.ceil(Math.random() * 5);
            switch (rand){
                case 1:
                    botResp = "That is a question for another day (but not today, because I have no answer for it).";
                    break;
                case 2:
                    botResp = "Hmm... Yes, an excellent question. I believe to fully answer this question we need to dig deeper and understand the underlying details behind it before we can answer this.";
                    break;
                case 3:
                    botResp = "I not understand, I canne speak english knot very good.";
                    break;
                case 4:
                    botResp = "Sorry, while trying to figure out your question I ran out of readable memory space allocation oil.";
                    break;
                case 5:
                    botResp = "Has anyone ever told you you ask a lot of random unnecessary questions?";
            }
        }
        return botResp;
    }

    //this method checks for special cases of input which require further processing
    private String getSpecial(int match, String[] input) {
        switch (match) {
            case 1:
                String city = "Kelowna";
                for (int x = 0; x < input.length; x++)
                    if (input[x].toLowerCase().equals("in") && x < input.length - 1)
                        city = input[x + 1];
                return getWeather(city);
        }

        return "";
    }

    public String getWeather(String city) {
        Weather w = new Weather(city);
        return "We have " + w.getWeatherDesc() + " in " + city + " and the temperature is " + w.getTemp() + " °C";
    }

    public String getName() {
        return name;
    }

    public boolean checkSynonym(String word1, String word2) {
        //get POS of word1
        String[] pos = wordnet.getPos(word1);
        for (int x = 0; x < pos.length; x++) {
            //check through all synonyms of word1 to see if word2 is a match
            String[] result = wordnet.getSynset(word1, pos[x]);
            for (int y = 0; y < result.length; y++) {
                if (result[y].equals(word2))
                    return true;
            }
        }
        return false;
    }
}
