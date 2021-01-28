package by.tc.task04.server.parse;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;

import java.util.ArrayList;

public interface GeneralTextParser {
    ArrayList<Sentence> parseTextToSentences(Text textToParse);
}
