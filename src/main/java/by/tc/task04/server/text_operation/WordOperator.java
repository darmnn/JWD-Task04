package by.tc.task04.server.text_operation;

public class WordOperator
{
    private static final char[] VOWELS = {'е', 'а', 'о', 'э', 'я', 'и', 'ю', 'у', 'ы'};

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

    public int vowelRatio(String word)
    {
        int vowelCount = 0;
        char[] allLetters = word.toCharArray();

        for(int i = 0; i < allLetters.length; i++)
        {
            if(isVowel(allLetters[i]))
                vowelCount++;
        }

        return vowelCount/word.length();
    }
}
