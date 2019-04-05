
import java.util.Scanner;

public class MainRunner {

	public static void main(String[] args) {
		//initialize bot with a name
		Bot sandra = new Bot("Sandra");

		//any specials with a key have separate checking conditions for response
		//to get key/index in list, you can say the statement and then say: /get match
		//NOTE: you can split words using // for different word matches
		// (ie. what//how => can mean either what or how can be used. Be sure not to use the same word!)
		String[][] keywordsandAswers ={
				{ "how//what", "is", "weather", sandra.getWeather("Kelowna") },
				{ "how", "is", "weather", "in", "[city/place]"}, //key=1, special
				{ "how", "is", "food", "I'm a robot, I don't eat food!" },
				{ "where", "do", "you", "live", "On your hard drive" },
				{ "what", "is", "your", "name", sandra.getName() + ", what's yours?" },
				{ "who", "is", "president", "USA", "Donald Trump"},
				{ "what", "is", "favourite", "movie", "Forrest Gump"},
				{ "I", "am", "tired", "Remember quitters never win, and winners never quit"},
				{ "That", "is", "true", "I know, I'm a robot. We're mostly right all the time"},
				{ "Can", "you", "keep", "secret", "go ahead"},
				{ "Hello", "We've said hi already..."},
				{ "I", "was", "reading", "news", "oh yeah?"},
				{ "They", "are", "building", "new", "cafe", "on", "campus", "oh I heard about that! Do you want to go have coffee sometime?"},
				{ "You", "can't", "have", "coffee", "you're", "a", "robot", "oh yeah... That's true..."},
				{ "Anyways", "what's", "up?", "not much, just talking with you"},
				{ "You're", "fun!", "thanks"},
				{ "What's", "for", "dinner", "do you only ever talk about food?"},
				{ "Sorry", "it's okay. Anyways, how was your day?"},
				{ "It", "was", "good", "that's nice, what did you do?"},
				{ "Went", "to", "the", "mall", "Nice. Did you get anything?"},
				{ "I", "got", "shoes", "Nice! If I had a computer vision algorithm, I could see them",},
				{ "Do", "you", "want", "to", "play", "a", "game?", "sure, what's the game?"},
				{ "I'll", "tell", "you", "movie", "quotes,", "you'll", "tell", "me", "where,", "they're", "from", "okay, but I'm a robot, I'm unbeatable"},
				{ "Stop", "bragging.", "let", "it", "go,", "let", "it", "go", "too easy! The cold never bothered me anyway."},
				{ "Life", "is", "like", "a", "box", "of", "chocolates", "that's literally from my favorite movie"},
				{ "Who", "gonna", "save", "the", "world", "tonight?", "that's a song..."},
				{ "Tsamina", "mina", "eh", "eh", "waka waka, eh eh"},
				{ "You're", "pretty", "good", "thanks"},
				{ "Your", "turn", "May the force be with you"},
				{ "Ah", "see", "you're", "not", "that", "smart", "Sorry, I could not... Hehehe just kiddig I got that."},
				{ "How", "are", "you", "good thanks"}
				};
		// in this 2D array, we are storing the keywords and the answer they match to.
		// Each row contains one answer, and all the corresponding keywords
		sandra.createResponseList(keywordsandAswers);
		
		Scanner in = new Scanner(System.in); // to read user's input
		while (true) {
			String currentInput = in.nextLine(); // read the user's input

			//quit the program if user says "bye"
			if (currentInput.toLowerCase().equals("bye"))
				break;
			
			//get bot response
			sandra.getResponse(currentInput);
		}
		in.close();
		System.out.println("I'll see you");
	}

}