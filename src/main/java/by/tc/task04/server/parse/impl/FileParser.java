package by.tc.task04.server.parse.impl;

import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.GeneralFileParser;

import java.io.*;

public class FileParser implements GeneralFileParser
{
    private File fileToParse;

    private static final String PATH = ".\\resources\\C++ guide.txt";
    private static final String SPACE = " ";

    public FileParser()
    {
        fileToParse = new File(PATH);
    }

    public Text parseFileToText() throws FileNotFoundException, IOException
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
                textContent.append(lineToRead + SPACE);
                lineToRead = reader.readLine();
            }
        }
        catch (FileNotFoundException ex)
        {
           throw ex;
        }
        catch (IOException exx)
        {
            throw exx;
        }
        text = new Text(textContent.toString());

        return text;
    }
}
