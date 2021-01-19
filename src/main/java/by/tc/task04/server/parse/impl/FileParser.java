package by.tc.task04.server.parse.impl;

import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.GeneralFileParser;

import java.io.*;

public class FileParser implements GeneralFileParser
{
    private File fileToParse;

    public FileParser(String path)
    {
        fileToParse = new File(path);
    }

    public Text parseFileToText()
    {
        Text text = null;

        StringBuilder textContent = new StringBuilder();

        try
        {
            FileReader fileReader = new FileReader(fileToParse);
            BufferedReader reader = new BufferedReader(fileReader);

            String lineToRead = reader.readLine();
            while(lineToRead != null)
            {
                textContent.append(lineToRead);
                lineToRead = reader.readLine();
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch (IOException exx)
        {
            exx.printStackTrace();
            return null;
        }

        text = new Text(textContent.toString());

        return text;
    }
}
