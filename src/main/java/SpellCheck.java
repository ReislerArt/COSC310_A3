import com.google.gson.*;
import com.mashape.unirest.http.*;
import org.languagetool.*;
import org.languagetool.language.*;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

/**
 * A class that performs a spellcheck on a given sentences.
 */
public class SpellCheck {

    String sentence = "";
    String fixedSentence = "";

    /**
     * Create a new SpellCheck object by setting sentence member field
     *
     * @param given - Sentence to be check
     * @throws Exception
     */
    public SpellCheck(String given) throws Exception {
        setNewSentence(given);
    }

    /**
     * Set the sentence field and call the process method to do processing
     *
     * @param sentence Sentence to be processed
     * @throws Exception
     */
    public void setNewSentence(String sentence) throws Exception {
        this.sentence = sentence;
        process();
    }

    /**
     * Process the given sentence and produce a correct sentences if there are any issues
     *
     * @throws Exception
     */
    private void process() throws Exception {
        JLanguageTool jTool = new JLanguageTool(new AmericanEnglish());
        List<RuleMatch> matches = jTool.check(this.sentence);

        StringBuffer correctSentence = new StringBuffer(sentence);
        int offset = 0;
        /*
         Pick the best suggestion for each words
         */
        for (RuleMatch match : matches) {

            correctSentence.replace(match.getFromPos() - offset, match.getToPos() - offset, match.getSuggestedReplacements().get(0));
            offset += (match.getToPos() - match.getFromPos() - match.getSuggestedReplacements().get(0).length());
        }
        this.fixedSentence = correctSentence.toString();
    }

    /**
     * Return Corrected Sentence, will return the same sentence if there are no error
     *
     * @return Corrected Sentence
     */
    public String getCorrectedSentence() {
        return this.fixedSentence;
    }

    /**
     * Get unprocessed sentence
     *
     * @return unprocessed sentence
     */
    public String getRawSentence() {
        return sentence;
    }
}
