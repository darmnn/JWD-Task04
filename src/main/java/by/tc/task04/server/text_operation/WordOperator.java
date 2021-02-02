package by.tc.task04.server.text_operation;

public class WordOperator
{
    private static final char[] VOWELS = {'е', 'а', 'о', 'э', 'я', 'и', 'ю', 'у', 'ы', 'ё', 'e', 'u', 'i',
                                            'o', 'a'};

    WordOperator(){

    }

    public boolean isVowel(char letter)
    {
        for(int i = 0; i < VOWELS.length; i++)
        {
            if(letter == VOWELS[i])
                return true;
        }
        return false;
    }

    public boolean beginsWithVowel(String word)
    {
        if(isVowel(word.charAt(0))) return true;
        else return false;
    }

    public double vowelRatio(String word)
    {
        double vowelCount = 0.0;
        char[] allLetters = word.toCharArray();

        for(int i = 0; i < allLetters.length; i++)
        {
            if(isVowel(allLetters[i]))
                vowelCount++;
        }

        return vowelCount/word.length();
    }

    public double letterRatio(String word, char letter)
    {
        double letterCount = 0.0;
        char[] allLetters = word.toCharArray();

        for(int i = 0; i < allLetters.length; i++)
        {
            if(allLetters[i] == letter)
                letterCount++;
        }

        return letterCount/word.length();
    }

    public String deleteFirstLetterOccurrences(String word)
    {
        char firstLetter = word.toLowerCase().charAt(0);
        StringBuilder newWord = new StringBuilder(word);
        for(int i = 1; i < newWord.length(); i++)
        {
            if(newWord.charAt(i) == firstLetter)
                newWord.deleteCharAt(i);
        }

        return newWord.toString();
    }
}
