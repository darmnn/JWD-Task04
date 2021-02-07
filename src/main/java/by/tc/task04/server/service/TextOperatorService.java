package by.tc.task04.server.service;

import by.tc.task04.entity.Text;
import by.tc.task04.server.text_operation.TextOperator;

import java.util.ArrayList;

public class TextOperatorService
{
    private TextOperator textOperator;

    public TextOperatorService(TextOperator textOperator)
    {
        this.textOperator = textOperator;
    }

    public ArrayList<Text> getProcessedTexts()
    {
        ArrayList<Text> processedTexts = new ArrayList<Text>();

        processedTexts.add(textOperator.getSentencesWithRepetitiveWords());
        processedTexts.add(textOperator.getSentencesBySize());
        processedTexts.add(textOperator.getUniqueWord());
        processedTexts.add(textOperator.getWordsFromQuestions(5));
        processedTexts.add(textOperator.changeLastAndFirstWords());
        processedTexts.add(textOperator.getSortedText());
        processedTexts.add(textOperator.sortByVowelRatio());
        processedTexts.add(textOperator.sortByFirstVowel());
        processedTexts.add(textOperator.sortByLetterRatio('а'));
        ArrayList<String> words = new ArrayList<String>();
        words.add("C++");
        words.add("язык");
        words.add("программист");
        processedTexts.add(textOperator.sortWordsByOccurrence(words));
        processedTexts.add(textOperator.deleteSubstring("Цель", "дней"));
        processedTexts.add(textOperator.deleteWordsStartingWithConsonants(8));
        processedTexts.add(textOperator.sortByLetterRatio1('о'));
        processedTexts.add(textOperator.deleteFirstLetterOccurrencesFromAllWords());
        processedTexts.add(textOperator.changeWordToSubstring(6, "замененная подстрока"));

        return processedTexts;
    }
}
