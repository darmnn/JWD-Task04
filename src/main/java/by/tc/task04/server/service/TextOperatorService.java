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

    public Text[] getProcessedTexts()
    {

        Text[] processedTexts = new Text[14];

        processedTexts[0] = textOperator.getSentencesWithRepetitiveWords();
        processedTexts[1] = textOperator.getSentencesBySize();
        processedTexts[2] = textOperator.getUniqueWord();
        processedTexts[4] = textOperator.changeLastAndFirstWords();
        processedTexts[5] = textOperator.getSortedText();
        processedTexts[6] = textOperator.sortByVowelRatio();
        processedTexts[7] = textOperator.sortByFirstVowel();
        processedTexts[13] = textOperator.deleteFirstLetterOccurrencesFromAllWords();

        return processedTexts;
    }
}
