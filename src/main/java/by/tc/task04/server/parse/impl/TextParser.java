package by.tc.task04.server.parse.impl;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.GeneralTextParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements GeneralTextParser
{
    private static final String SENTENCE = "(?Us).+?(?:[?!.]|$)";
    private static final String SPACES = "\\s+";
    private static final String PUNCTUATION_MARKS= "[\"(),;:?!.$]";

    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";
    private static final String POINT = ".";
    private static final String DASH = "—";

    public TextParser()
    {
    }

    public ArrayList<Sentence> parseTextToSentences(Text textToParse)
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

    public ArrayList<String> parseSentenceToWords(Sentence sentence)
    {
        String[] wordsSplittedBySpace = sentence.getContent().split(SPACES);
        ArrayList<String> wordsFromSentence = new ArrayList<String>();

        for(int i = 0; i < wordsSplittedBySpace.length; i++)
        {
            if(!wordsSplittedBySpace[i].replaceAll(PUNCTUATION_MARKS, EMPTY_STRING).equals(EMPTY_STRING))
                wordsFromSentence.add(wordsSplittedBySpace[i].replaceAll(PUNCTUATION_MARKS, EMPTY_STRING));
        }

        wordsFromSentence.remove(POINT);
        while(wordsFromSentence.contains(DASH))
            wordsFromSentence.remove(DASH);

        return wordsFromSentence;
    }

    public Sentence getSentenceFromWords(ArrayList<String> words)
    {
        StringBuilder sentenceContent = new StringBuilder();

        for(String word : words)
        {
            sentenceContent.append(word);
            sentenceContent.append(SPACE);
        }

        sentenceContent.append(POINT);
        return new Sentence(sentenceContent.toString());
    }
}
