package by.tc.task04.server.parse;

import by.tc.task04.entity.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GeneralFileParser {
    public Text parseFileToText() throws FileNotFoundException, IOException;
}
