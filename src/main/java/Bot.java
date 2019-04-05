/**
 * Created by Agent on 2019-02-12.
 */
public class Bot {
    String name;
    String[][] keyAns;
    int bestMatch;
    SpellCheck spell;

    Bot(String name) {
        this.name = name;
        System.out.println("Hi, my name is Sandra");
        bestMatch = -1;
        //initialize spell checker
        try {
            spell = new SpellCheck("");
        }catch (Exception e){
        }
    }

    public void createResponseList(String[][] list) {
        keyAns = list;
        for(int x = 0; x<keyAns.length;x++){
            for(int y = 0; y<keyAns[x].length-1;y++){
                keyAns[x][y] = keyAns[x][y].replaceAll("[^A-Za-z0-9 ]", "");
            }
        }
    }

    public void getResponse(String input) {
        //for getting location of previous match
        if (input.toLowerCase().equals("/get match")) {
            if (bestMatch != -1)
                System.out.println(bestMatch);
            return;
        }

        //try to check for spelling errors
        try {
            spell.setNewSentence(input);
        }catch (Exception e){}
        input = spell.getCorrectedSentence();

        //remove any non-alphabet/space characters
        input = input.replaceAll("[^A-Za-z0-9 ]", "");
        //split input into separate words
        String[] in = input.split(" ");

//        System.out.println(input);

        //find best match for response
        bestMatch = -1;
        int matchCount = 0;
        //first loop goes through list of sentences to match
        for (int x = 0; x < keyAns.length; x++) {
            int pos = 0;
            //goes through list of input words
            for (int y = 0; y < in.length; y++) {
                //split all words with a backslash (/) and compare each split word individually
                String[] keys = keyAns[x][pos].split("//");
                for(int i = 0; i<keys.length;i++) {
                    //if there is a match, the position increments, and once all
                    //words from keywords are matched it is the best match
                    if (in[y].toLowerCase().equals((keys[i]).toLowerCase())) {
                        pos++;
                        //if all keywords are matched
                        if (pos >= keyAns[x].length - 1 && matchCount < pos) {
                            matchCount = pos;
                            bestMatch = x;
                            y = in.length;
                        }
                    }
                }
            }
        } //end of loops
        //check if there is a special case for these keywords
        String sp = this.getSpecial(bestMatch, in);

        if(sp.length()>0){
            System.out.println(sp);
        }else if (bestMatch != -1) {
            System.out.println(keyAns[bestMatch][keyAns[bestMatch].length - 1]);
        } else {
            System.out.println("Sorry, I could not understand what you are saying.");
        }
    }

    //this method checks for special cases of input which require further processing
    private String getSpecial(int match, String[] input) {
        switch (match) {
            case 1:
                String city = "Kelowna";
                for (int x = 0; x < input.length; x++)
                    if (input[x].toLowerCase().equals("in") && x < input.length-1)
                        city = input[x+1];
                return getWeather(city);
        }

        return "";
    }

    public String getWeather(String city) {
        Weather w = new Weather(city);
        return "We have " + w.getWeatherDesc() + " in " + city + " and the temperature is " + w.getTemp() + " °C";
    }

    public String getName(){
        return name;
    }
}
