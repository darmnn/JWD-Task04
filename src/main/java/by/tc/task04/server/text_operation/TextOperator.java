package by.tc.task04.server.text_operation;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.impl.TextParser;

import java.util.*;

public class TextOperator
{
    private TextParser textParser;
    private WordOperator wordOperator;
    private Text text;
    private ArrayList<Sentence> allSentencesFromText;
    private ArrayList<String> allWordsFromText;

    private static final String QUESTION_MARK = "?";
    private static final int FIRST = 0;
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";

    public TextOperator(Text text)
    {
        this.text = text;
        this.textParser = new TextParser();
        this.wordOperator = new WordOperator();
        allSentencesFromText = textParser.parseTextToSentences(text);

        allWordsFromText = new ArrayList<String>();
        for(Sentence sentence : allSentencesFromText)
        {
            allWordsFromText.addAll(textParser.parseSentenceToWords(sentence));
        }
    }

    public Text getSentencesWithRepetitiveWords()
    {
        ArrayList<Sentence> sentencesWithRepetitiveWords = new ArrayList<Sentence>();

        for(Sentence sentence: allSentencesFromText)
        {
            ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);

            for(int i = 0; i < wordsFromSentence.size(); i++)
            {
                for(int j = i + 1; j < wordsFromSentence.size(); j++)
                {
                    if(wordsFromSentence.get(i).equals(wordsFromSentence.get(j)))
                    {
                        if(!sentencesWithRepetitiveWords.contains(sentence))
                            sentencesWithRepetitiveWords.add(sentence);
                    }
                }
            }
        }

        return new Text(sentencesWithRepetitiveWords);
    }

    private HashMap<Sentence, Integer> sortByValue(HashMap<Sentence, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Sentence, Integer> > list = new LinkedList<Map.Entry<Sentence, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Sentence, Integer> >() {
            public int compare(Map.Entry<Sentence, Integer> o1,
                               Map.Entry<Sentence, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Sentence, Integer> temp = new LinkedHashMap<Sentence, Integer>();

        for (Map.Entry<Sentence, Integer> aa : list)
        {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private HashMap<String, Integer> sortByValue1(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();

        for (Map.Entry<String, Integer> aa : list)
        {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public Text getSentencesBySize()
    {
        ArrayList<Sentence> sentencesSortedBySize= new ArrayList<Sentence>();
        HashMap<Sentence, Integer> sentencesAndSizesMap = new HashMap<Sentence, Integer>();

        for(Sentence sentence : allSentencesFromText)
            sentencesAndSizesMap.put(sentence, textParser.parseSentenceToWords(sentence).size());

        sentencesAndSizesMap = sortByValue(sentencesAndSizesMap);

        for(Sentence sentence : sentencesAndSizesMap.keySet())
            sentencesSortedBySize.add(sentence);

        return new Text(sentencesSortedBySize);
    }

    public Text getUniqueWord()
    {
        String uniqueWord = "";

        Sentence firstSentence = allSentencesFromText.get(FIRST);
        ArrayList<String> wordsFromFirstSentence = textParser.parseSentenceToWords(firstSentence);
        ArrayList<Sentence> sentencesExceptFirst = new ArrayList<Sentence>();

        for(int i = 1; i < allSentencesFromText.size(); i++)
            sentencesExceptFirst.add(allSentencesFromText.get(i));

        for(String word : wordsFromFirstSentence)
        {
            if(!sentencesExceptFirst.contains(word))
                uniqueWord = word;
        }

        return new Text(uniqueWord);
    }

    public Text getWordsFromQuestions(int wordLength)
    {
        ArrayList<String> resultWords = new ArrayList<String>();
        ArrayList<Sentence> questionSentences = new ArrayList<Sentence>();

        for(Sentence sentence : allSentencesFromText)
        {
            if(sentence.getContent().contains(QUESTION_MARK))
                questionSentences.add(sentence);
        }

        for(Sentence sentence : questionSentences)
        {
            ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);

            for(String word : wordsFromSentence)
            {
                if(word.length() == wordLength && !resultWords.contains(word))
                    resultWords.add(word);

            }
        }

        StringBuilder allResultWords = new StringBuilder();

        for(String word : resultWords)
        {
            allResultWords.append(word);
            allResultWords.append(SPACE);
        }

        return new Text(allResultWords.toString());
    }

    public Text changeLastAndFirstWords()
    {
        ArrayList<Sentence> result = new ArrayList<Sentence>();

        for(Sentence sentence : allSentencesFromText)
        {
            ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);

            if(wordsFromSentence.size() > 1)
            {
                String firstWord = wordsFromSentence.get(FIRST);
                String lastWord = wordsFromSentence.get(wordsFromSentence.size() - 1);

                wordsFromSentence.remove(FIRST);
                wordsFromSentence.remove(wordsFromSentence.size() - 1);
                wordsFromSentence.add(FIRST, lastWord);
                wordsFromSentence.add(wordsFromSentence.size(), firstWord);
            }
            result.add(textParser.getSentenceFromWords(wordsFromSentence));
        }

        return new Text(result);
    }

    public Text getSortedText()
    {
        ArrayList<String> wordsToSort = new ArrayList<String>();
        wordsToSort.addAll(allWordsFromText);

        Collections.sort(wordsToSort);

        StringBuilder content = new StringBuilder();
        for(int i = 0; i < wordsToSort.size(); i++)
        {
            if(i != 0 && wordsToSort.get(i).charAt(0) != wordsToSort.get(i - 1).charAt(0))
                content.append("\n\t");

            content.append(wordsToSort.get(i) + SPACE);
        }

        return new Text(content.toString());
    }

    public Text sortByVowelRatio()
    {
        StringBuilder content = new StringBuilder();
        ArrayList<String> wordsToSort = new ArrayList<String>();
        wordsToSort.addAll(allWordsFromText);

        String[] wordsToSortArr = new String[wordsToSort.size()];

        for(int i = 0; i < wordsToSort.size(); i++)
            wordsToSortArr[i] = wordsToSort.get(i);

        for(int i = 0; i < wordsToSortArr.length; i++)
        {
            for(int j = 1; j < wordsToSortArr.length; j++)
            {
                if(wordOperator.vowelRatio(wordsToSortArr[j - 1]) > wordOperator.vowelRatio(wordsToSortArr[j]))
                {
                    String temp = wordsToSortArr[j- 1];
                    wordsToSortArr[j - 1] = wordsToSortArr[j];
                    wordsToSortArr[j] = temp;
                }
            }
        }

        for(int i = 0; i < wordsToSortArr.length; i++)
        {
            content.append(wordsToSortArr[i] + SPACE);
        }

        return new Text(content.toString());
    }

    public Text sortByFirstVowel()
    {
        ArrayList<String> wordsBeginWithVowel = new ArrayList<String>();

        for(String word : allWordsFromText)
        {
            if(wordOperator.beginsWithVowel(word))
                wordsBeginWithVowel.add(word);
        }

        Collections.sort(wordsBeginWithVowel);

        StringBuilder content = new StringBuilder();
        for(String word : wordsBeginWithVowel)
            content.append(word + SPACE);

        return new Text(content.toString());
    }

    public Text sortByLetterRatio(char letter)
    {
        StringBuilder content = new StringBuilder();
        ArrayList<String> wordsToSort = new ArrayList<String>();
        wordsToSort.addAll(allWordsFromText);

        String[] wordsToSortArr = new String[wordsToSort.size()];

        for(int i = 0; i < wordsToSort.size(); i++)
            wordsToSortArr[i] = wordsToSort.get(i);

        for(int i = 0; i < wordsToSortArr.length; i++)
        {
            for(int j = 1; j < wordsToSortArr.length; j++)
            {
                if(wordOperator.letterRatio(wordsToSortArr[j - 1], letter) > wordOperator.letterRatio(wordsToSortArr[j], letter))
                {
                    String temp = wordsToSortArr[j- 1];
                    wordsToSortArr[j - 1] = wordsToSortArr[j];
                    wordsToSortArr[j] = temp;
                }
            }
        }

        for(int i = 0; i < wordsToSortArr.length; i++)
        {
            content.append(wordsToSortArr[i] + SPACE);
        }

        return new Text(content.toString());
    }

    public Text sortWordsByOccurrence(ArrayList<String> words)
    {
        HashMap<String, Integer> wordsOccurrences = new HashMap<String, Integer>();

        for(String word : words)
        {
            int occurrenceCount = 0;
            for(Sentence sentence : allSentencesFromText)
            {
                ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);

                for(String wordFromSentence : wordsFromSentence)
                {
                    if(wordFromSentence.equals(word))
                        occurrenceCount++;
                }
            }
            wordsOccurrences.put(word, occurrenceCount);
        }

        wordsOccurrences = sortByValue1(wordsOccurrences);

        StringBuilder content = new StringBuilder();
        for(String word : wordsOccurrences.keySet())
            content.append(word + SPACE);

        return new Text(content.toString());
    }

    public Text deleteSubstring(String startWord, String endWord)
    {
        Text resultText = new Text(EMPTY_STRING);

        boolean textEnd = false;
        int startId = 0;
        while(!textEnd)
        {
            int index = text.getContent().indexOf(startWord, startId);

            if(index != -1 && text.getContent().indexOf(endWord, index + startWord.length()) != -1)
            {
                int endWordId = text.getContent().indexOf(endWord, index + startWord.length());

                StringBuilder newText = new StringBuilder(text.getContent());
                newText.delete(index, endWordId + endWord.length());
                resultText.setContent(newText.toString());

                startId = endWordId + endWord.length();
            }
            else textEnd = true;
        }

        return resultText;
    }

    public Text deleteWordsStartingWithConsonants(int wordLength)
    {
        ArrayList<Sentence> newSentences = new ArrayList<Sentence>();

        for(Sentence sentence : allSentencesFromText)
        {
            StringBuilder sentenceContent = new StringBuilder(sentence.getContent());

            ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);
            for(String word : wordsFromSentence)
            {
                if(!wordOperator.beginsWithVowel(word) && word.length() == wordLength)
                {
                    int startId = sentenceContent.indexOf(word);
                    sentenceContent.delete(startId, startId + word.length());
                }
            }

            newSentences.add(new Sentence(sentenceContent.toString()));
        }

        return new Text(newSentences);
    }

    public Text sortByLetterRatio1(char letter)
    {
        StringBuilder content = new StringBuilder();
        ArrayList<String> wordsToSort = new ArrayList<String>();
        wordsToSort.addAll(allWordsFromText);

        String[] wordsToSortArr = new String[wordsToSort.size()];

        for(int i = 0; i < wordsToSort.size(); i++)
            wordsToSortArr[i] = wordsToSort.get(i);

        for(int i = 0; i < wordsToSortArr.length; i++)
        {
            for(int j = 1; j < wordsToSortArr.length; j++)
            {
                if(wordOperator.letterRatio(wordsToSortArr[j - 1], letter) < wordOperator.letterRatio(wordsToSortArr[j], letter))
                {
                    String temp = wordsToSortArr[j- 1];
                    wordsToSortArr[j - 1] = wordsToSortArr[j];
                    wordsToSortArr[j] = temp;
                }
            }
        }

        for(int i = 0; i < wordsToSortArr.length; i++)
        {
            content.append(wordsToSortArr[i] + SPACE);
        }

        return new Text(content.toString());
    }

    public Text deleteFirstLetterOccurrencesFromAllWords()
    {
        ArrayList<Sentence> newSentences = new ArrayList<Sentence>();

        for(Sentence sentence : allSentencesFromText)
        {
            ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);
            ArrayList<String> newWords = new ArrayList<String>();

            for(String word : wordsFromSentence)
                newWords.add(wordOperator.deleteFirstLetterOccurrences(word));

            newSentences.add(textParser.getSentenceFromWords(newWords));
        }

        return new Text(newSentences);
    }

    public Text changeWordToSubstring(int wordLength, String substring) {
        ArrayList<Sentence> newSentences = new ArrayList<Sentence>();

        Sentence sentence = allSentencesFromText.get(FIRST);

        ArrayList<String> wordsFromSentence = textParser.parseSentenceToWords(sentence);
        ArrayList<String> newWords = new ArrayList<String>();

        for (String word : wordsFromSentence) {
            if (word.length() == wordLength)
                newWords.add(substring);
            else newWords.add(word);
        }

        newSentences.add(textParser.getSentenceFromWords(newWords));
        for (int i = 1; i < allSentencesFromText.size(); i++)
            newSentences.add(allSentencesFromText.get(i));

        return new Text(newSentences);
    }
}
