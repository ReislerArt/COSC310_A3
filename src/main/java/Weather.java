import com.google.gson.*;
import com.mashape.unirest.http.*;

/**
 * A class for getting weather information
 */
public class Weather {
    String city;
    final String API_ID = "c9df1e75b391932eeaa45a83ef9d9860"; //DONT CHANGE, needed for auth
    String json;
    String weatherMain; //Example: Clear
    String weatherDesc; //Example: clear sky
    String temp; //Example: -1

    /**
     * Constructor for Weather
     * @param location
     */
    public Weather(String location){
        city = location; //Initialize City
        String requestUrl = "http://api.openweathermap.org/data/2.5/weather?units=metric&APPID="+API_ID+"&q="+city;
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.get(requestUrl).asJson();
            json = jsonResponse.getBody().toString();
            setWeatherInfo();
            setTemp();
        }catch(Exception e){
            //set value to unknown if city is not found
            temp = "-999";
            weatherMain = "unknown";
            weatherDesc = "unknown";
        }
    }
    /**
     * Return temp in celcius
     * @return Temperature
     */
    public String getTemp(){
        return temp;
    }
    public String getWeatherMain(){
        return weatherMain;
    }
    public String getWeatherDesc(){
        return weatherDesc;
    }
    /**
     * Set current weather (Weather main and weather desc)
     * @throws Exception
     */
    public void setWeatherInfo(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Convert JSON to json array then json object to make it parseable
        JsonObject body = gson.fromJson(json, JsonObject.class);
        JsonArray results = body.get("weather").getAsJsonArray();
        JsonObject firstResult = results.get(0).getAsJsonObject();

        JsonElement weatherMainElem = firstResult.get("main");
        JsonElement weatherDescElem = firstResult.get("description");
        weatherMain = weatherMainElem.getAsString();
        weatherDesc = weatherDescElem.getAsString();
    }
    /**
     * Set temperature
     * @throws Exception
     */
    public void setTemp(){

        //attempt to retrieve value of temp from json object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject body = gson.fromJson(json, JsonObject.class);
        JsonObject insideBody = body.getAsJsonObject("main");
        JsonElement weatherTempElem = insideBody.get("temp");
        temp = weatherTempElem.getAsString();
    }
}
