/**
 * Created by Agent on 2019-04-04.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuiMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));

        //initialize some parameters
        Bot sandra = new Bot("Sandra");
        initializeBot(sandra);

        String userCol = "#ffffff";
        String botCol = "#bbdefb";
        String backCol = "#29434e";
        String btnCol = "#546e7a";

        //create scene and set properties
        Scene scene = new Scene(root, 630, 400);

        stage.setTitle("ChatBot");
        stage.setScene(scene);
        stage.setResizable(false);
        //get scroll pane and set its colour
        ScrollPane scroll = (ScrollPane) scene.lookup("#scrollPane");
        scroll.setStyle("-fx-background: " + backCol + "; -fx-background-color: " + backCol + ";");

        //change button text colour
        Button btn = (Button) scene.lookup("#btn");
        btn.setStyle("-fx-text-fill: whitesmoke; -fx-background-color: " + btnCol + ";");

        //get textarea text
        TextArea text = (TextArea) scene.lookup("#textArea");
        text.setWrapText(true);

        stage.show();
        VBox cont = new VBox();
        cont.setSpacing(10);

        //test
        sandra.checkSynonym();

        //sandra saying hello
        String botName = sandra.getName();
        String botIntro = botName + ": Hi! My name is " + botName + ".";
        outputMsg(botIntro, botCol, cont, scene);

        //action event button for sending message
        Button button = (Button) scene.lookup("#btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // create a label
                String userInput = text.getText();
                outputMsg("Me: " + userInput, userCol, cont, scene);

                //get bot response
                String botResp = sandra.getResponse(userInput);
                outputMsg(botName + ": " + botResp, botCol, cont, scene);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void outputMsg(String msg, String col, VBox cont, Scene scene) {
        ScrollPane scroll = (ScrollPane) scene.lookup("#scrollPane");
        // create a label
        Text lbl = new Text(msg);
        lbl.setFill(Color.web(col));
        lbl.setFont(new Font(14));
        lbl.setWrappingWidth(550);
        //add lbl to scrollPane
        cont.getChildren().add(lbl);
        scroll.setContent(cont);
        //scroll the scrollPane to bottom
        scroll.vvalueProperty().bind(cont.heightProperty());
    }

    private void initializeBot(Bot sandra) {
        //any specials with a key have separate checking conditions for response
        //to get key/index in list, you can say the statement and then say: /get match
        //NOTE: you can split words using // for different word matches
        // (ie. what//how => can mean either what or how can be used. Be sure not to use the same word!)
        String[][] keywordsandAswers = {
                {"how//what", "is", "weather", sandra.getWeather("Kelowna")},
                {"how", "is", "weather", "in", "[city/place]"}, //key=1, special
                {"how", "is", "food", "I'm a robot, I don't eat food!"},
                {"where", "do", "you", "live", "On your hard drive"},
                {"what", "is", "your", "name", sandra.getName() + ", what's yours?"},
                {"who", "is", "president", "USA", "Donald Trump"},
                {"what", "is", "favourite", "movie", "Forrest Gump"},
                {"I", "am", "tired", "Remember quitters never win, and winners never quit"},
                {"That", "is", "true", "I know, I'm a robot. We're mostly right all the time"},
                {"Can", "you", "keep", "secret", "go ahead"},
                {"Hello", "We've said hi already..."},
                {"I", "was", "reading", "news", "oh yeah?"},
                {"They", "are", "building", "new", "cafe", "on", "campus", "oh I heard about that! Do you want to go have coffee sometime?"},
                {"You", "can't", "have", "coffee", "you're", "a", "robot", "oh yeah... That's true..."},
                {"Anyways", "what's", "up?", "not much, just talking with you"},
                {"You're", "fun!", "thanks"},
                {"What's", "for", "dinner", "do you only ever talk about food?"},
                {"Sorry", "it's okay. Anyways, how was your day?"},
                {"It", "was", "good", "that's nice, what did you do?"},
                {"Went", "to", "the", "mall", "Nice. Did you get anything?"},
                {"I", "got", "shoes", "Nice! If I had a computer vision algorithm, I could see them",},
                {"Do", "you", "want", "to", "play", "a", "game?", "sure, what's the game?"},
                {"I'll", "tell", "you", "movie", "quotes,", "you'll", "tell", "me", "where,", "they're", "from", "okay, but I'm a robot, I'm unbeatable"},
                {"Stop", "bragging.", "let", "it", "go,", "let", "it", "go", "too easy! The cold never bothered me anyway."},
                {"Life", "is", "like", "a", "box", "of", "chocolates", "that's literally from my favorite movie"},
                {"Who", "gonna", "save", "the", "world", "tonight?", "that's a song..."},
                {"Tsamina", "mina", "eh", "eh", "waka waka, eh eh"},
                {"You're", "pretty", "good", "thanks"},
                {"Your", "turn", "May the force be with you"},
                {"Ah", "see", "you're", "not", "that", "smart", "Sorry, I could not... Hehehe just kiddig I got that."},
                {"How", "are", "you", "good thanks"}
        };
        // in this 2D array, we are storing the keywords and the answer they match to.
        // Each row contains one answer, and all the corresponding keywords
        sandra.createResponseList(keywordsandAswers);
    }
}

