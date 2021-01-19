package by.tc.task04.server.parse.impl;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.GeneralTextParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements GeneralTextParser
{
    private Text textToParse;

    private static final String SENTENCE = "(?Us).+?(?:[?!.]|$)";
    private static final String SPACES = "\\s+";
    private static final String PUNCTUATION_MARKS= "[,;:.$]";
    private static final String NOTHING = "";

    public TextParser(Text textToParse)
    {
        this.textToParse = textToParse;
    }

    private ArrayList<Sentence> parseTextToSentences()
    {
        ArrayList<Sentence> sentencesFromText = new ArrayList<Sentence>();

        Pattern sentencePattern = Pattern.compile(SENTENCE);
        Matcher sentenceMatcher = sentencePattern.matcher(textToParse.getContent());

        int i = 0;
        while(sentenceMatcher.find(i))
        {
            int start = sentenceMatcher.start();
            int end = sentenceMatcher.end();

            i = end;
            sentencesFromText.add(new Sentence(textToParse.getContent().substring(start, end).trim()));
        }

        return sentencesFromText;
    }

    public Text getSentencesWithRepetitiveWords()
    {
        ArrayList<Sentence> sentencesWithRepetitiveWords = new ArrayList<Sentence>();
        ArrayList<Sentence> allSentencesFromText = parseTextToSentences();
        for(Sentence sentence: allSentencesFromText)
        {
            String[] wordsSplittedBySpace = sentence.getContent().split(SPACES);
            ArrayList<String> wordsFromSentence = new ArrayList<String>();

            for(int i = 0; i < wordsSplittedBySpace.length; i++)
                wordsFromSentence.add(wordsSplittedBySpace[i].replaceAll(PUNCTUATION_MARKS, NOTHING));

            for(String word : wordsFromSentence)
            {
                int firstOccurrenceId = sentence.getContent().indexOf(word);

                if(sentence.getContent().indexOf(word, firstOccurrenceId + 1) != -1)
                    sentencesWithRepetitiveWords.add(sentence);
            }
        }

        return new Text(sentencesWithRepetitiveWords);
    }
}
