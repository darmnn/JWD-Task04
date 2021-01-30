package by.tc.task04.server;

import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.impl.FileParser;
import by.tc.task04.server.text_operation.TextOperator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer
{
    private static final int PORT = 8030;

    public static void main(String[] args)
    {
        FileParser fileParser = new FileParser();
        Text text = fileParser.parseFileToText();

        TextOperator textOperator = new TextOperator(text);
        ArrayList<Text> processedTexts = new ArrayList<Text>();

        processedTexts.add(textOperator.getSentencesWithRepetitiveWords());
        processedTexts.add(textOperator.getSentencesBySize());
        processedTexts.add(textOperator.getUniqueWord());
        processedTexts.add(textOperator.getWordsFromQuestions(5));
        processedTexts.add(textOperator.changeLastAndFirstWords());
        processedTexts.add(textOperator.getSortedText());
        processedTexts.add(textOperator.sortByVowelRatio());

        Socket socket = null;
        try
        {
            ServerSocket server = new ServerSocket(PORT);
            socket = server.accept();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            for(Text processedText : processedTexts)
                objectOutputStream.writeObject(processedText);

            objectOutputStream.close();

            if(socket != null) socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
